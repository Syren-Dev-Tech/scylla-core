package com.github.syren_dev_tech.scylla.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition;
import com.github.syren_dev_tech.scylla.mobs.ai.AnimatedGoal;
import com.github.syren_dev_tech.scylla.mobs.client.TextureRenderer;
import com.github.syren_dev_tech.scylla.mobs.creatures.AnimationDefinition;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class CreatureBuilder<T extends CustomCreature> {

    private final ModRegister register;
    private final String name;

    private List<Function<T, AIGoal>> goals = new ArrayList<>();
    private Function<CreatureState<T>, ResourcePath> textures;
    private Function<CreatureState<T>, ResourcePath> masks;
    private List<MaskLayer<T>> maskLayers = new ArrayList<>();
    private TextureRenderer<T> textureRenderer;
    private Map<String, AnimationHandler<T>> animators = new HashMap<>();
    private CreatureFactory<T> factory;
    private float shadowSize = 0.5f;
    private boolean rideable = false;

    public CreatureBuilder(String name, ModRegister register, CreatureFactory<T> factory) {
        this.name = name;
        this.register = register;
        this.factory = factory;

        this.textures = t -> new ResourcePath(register.modId, "textures/entity/" + name + ".png");
    }

    public ModRegister getRegister() {
        return register;
    }

    public CreatureBuilder<T> withTextures(Function<CreatureState<T>, ResourcePath> textures) {
        this.textures = textures;
        return this;
    }

    public CreatureBuilder<T> withTextures(ResourcePath texture) {
        this.textures = state -> texture;
        return this;
    }

    public CreatureBuilder<T> withTexture(Function<CreatureState<T>, ResourcePath> textures) {
        return this.withTextures(textures);
    }

    public CreatureBuilder<T> withTexture(ResourcePath texture) {
        return this.withTextures(texture);
    }

    public CreatureBuilder<T> withMasks(Function<CreatureState<T>, ResourcePath> masks) {
        this.masks = masks;

        this.maskLayers.removeIf(layer -> layer.legacyDefault);
        this.maskLayers.add(MaskLayer.legacyDefault(masks));

        return this;
    }

    public CreatureBuilder<T> withMaskLayer(Function<CreatureState<T>, ResourcePath> mask, int rgbaColor) {
        this.maskLayers.add(MaskLayer.of(mask, state -> rgbaColor));
        return this;
    }

    public CreatureBuilder<T> withMaskLayer(Function<CreatureState<T>, ResourcePath> mask, Function<CreatureState<T>, Integer> colorSupplier) {
        this.maskLayers.add(MaskLayer.of(mask, colorSupplier));
        return this;
    }

    public CreatureBuilder<T> withMaskLayer(Function<CreatureState<T>, ResourcePath> mask, int red, int green, int blue, int alpha) {
        return this.withMaskLayer(mask, rgba(red, green, blue, alpha));
    }

    public CreatureBuilder<T> withMaskLayers(List<MaskLayer<T>> maskLayers) {
        this.maskLayers.addAll(maskLayers);
        return this;
    }

    public CreatureBuilder<T> clearMaskLayers() {
        this.maskLayers.clear();
        this.masks = null;
        return this;
    }

    public CreatureBuilder<T> withAiGoal(Function<T, AIGoal> goal) {
        this.goals.add(goal);
        return this;
    }

    public CreatureBuilder<T> withAiGoal(AIGoalDefinition<T> goalDefinition) {
        this.ensureGoalAnimator(goalDefinition);
        this.goals.add(entity -> this.createGoalFromDefinition(entity, goalDefinition));
        return this;
    }

    public CreatureBuilder<T> withAiGoal(Function<T, AIGoal> goal, String animatorName, String movingAnimation, String idleAnimation) {
        return this.withAiGoal(AIGoalDefinition.<T>define(goal).withAnimator(animatorName, movingAnimation, idleAnimation).build());
    }

    public CreatureBuilder<T> withAiGoal(Function<T, AIGoal> goal, String animatorName, String... animations) {
        return this.withAiGoal(AIGoalDefinition.<T>define(goal).withAnimator(animatorName, animations).build());
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals) {
        this.goals.addAll(goals);
        return this;
    }

    public CreatureBuilder<T> withAiGoalsFromDefinitions(List<AIGoalDefinition<T>> goalDefinitions) {
        for (AIGoalDefinition<T> goalDefinition : goalDefinitions) {
            this.withAiGoal(goalDefinition);
        }

        return this;
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String movingAnimation, String idleAnimation) {
        for (Function<T, AIGoal> goal : goals) {
            this.withAiGoal(goal, animatorName, movingAnimation, idleAnimation);
        }

        return this;
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String... animations) {
        for (Function<T, AIGoal> goal : goals) {
            this.withAiGoal(goal, animatorName, animations);
        }

        return this;
    }

    public CreatureBuilder<T> withAnimatedAiGoal(Function<T, AIGoal> goal, String animatorName, String movingAnimation, String idleAnimation) {
        return this.withAiGoal(goal, animatorName, movingAnimation, idleAnimation);
    }

    public CreatureBuilder<T> withAnimatedAiGoal(Function<T, AIGoal> goal, String animatorName, String... animations) {
        return this.withAiGoal(goal, animatorName, animations);
    }

    public CreatureBuilder<T> withAnimatedAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String movingAnimation, String idleAnimation) {
        return this.withAiGoals(goals, animatorName, movingAnimation, idleAnimation);
    }

    public CreatureBuilder<T> withAnimatedAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String... animations) {
        return this.withAiGoals(goals, animatorName, animations);
    }

    private AIGoal createGoalFromDefinition(T entity, AIGoalDefinition<T> goalDefinition) {
        AIGoal baseGoal = goalDefinition.getGoalFactory().apply(entity);

        if (!goalDefinition.hasAnimatorBinding()) {
            return baseGoal;
        }

        return new AnimatedGoal<>(entity, baseGoal).withAnimation(goalDefinition.getAnimatorName(), goalDefinition.getAnimations());
    }

    private void ensureGoalAnimator(AIGoalDefinition<T> goalDefinition) {
        if (!goalDefinition.hasAnimatorBinding()) {
            return;
        }

        this.animators.computeIfAbsent(goalDefinition.getAnimatorName(), name -> AnimationHandler.fromStateHandler(state -> null));
    }

    public CreatureBuilder<T> withShadowSize(float shadowSize) {
        this.shadowSize = shadowSize;
        return this;
    }

    public CreatureBuilder<T> withTextureRenderer(TextureRenderer<T> textureRenderer) {
        this.textureRenderer = textureRenderer;
        return this;
    }

    public CreatureBuilder<T> allowRiding() {
        this.rideable = true;
        return this;
    }

    public AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public CreatureFactory<T> getFactory() {
        return this.factory;
    }

    public String getName() {
        return name;
    }

    public Function<CreatureState<T>, ResourcePath> getTextures() {
        return textures;
    }

    public Function<CreatureState<T>, ResourcePath> getMasks() {
        return masks;
    }

    public List<MaskLayer<T>> getMaskLayers() {
        return maskLayers;
    }

    public float getShadowSize() {
        return shadowSize;
    }

    public TextureRenderer<T> getTextureRenderer() {
        return textureRenderer;
    }

    public List<Function<T, AIGoal>> getGoals() {
        return goals;
    }

    public Map<String, AnimationHandler<T>> getAnimators() {
        return animators;
    }

    public boolean isRideable() {
        return rideable;
    }

    public CreatureRegistrar<T> register() {
        var registry = this.register.mobRegistry.register(this);

        var registrar = new CreatureRegistrar<T>(this, registry, this::createAttributes);
        this.register.mobRegistry.entities.put(this.name, registrar);

        return registrar;
    }

    public CreatureBuilder<T> addAnimator(String name, Function<CreatureState<T>, AnimationDefinition> handler) {
        this.animators.put(name, AnimationHandler.fromStateHandler(handler));

        return this;
    }

    public CreatureBuilder<T> addAnimator(String name, AnimationHandler<T> handler) {
        this.animators.put(name, handler);

        return this;
    }

    public interface CreatureFactory<T extends CustomCreature> {
        T create(EntityType<? extends CustomCreature> type, Level world, CreatureBuilder<T> builder);
    }

    public static int rgba(int red, int green, int blue, int alpha) {
        return ((red & 0xFF) << 24) | ((green & 0xFF) << 16) | ((blue & 0xFF) << 8) | (alpha & 0xFF);
    }

    public static <T extends CustomCreature> AnimationResourceSelector<T> animationResources(String animatorName, ResourcePath fallback) {
        return new AnimationResourceSelector<>(animatorName, fallback);
    }

    public static class AnimationResourceSelector<T extends CustomCreature> {
        private final String animatorName;
        private final ResourcePath fallback;
        private final Map<String, ResourcePath> animationResources = new LinkedHashMap<>();

        private AnimationResourceSelector(String animatorName, ResourcePath fallback) {
            this.animatorName = animatorName;
            this.fallback = fallback;
        }

        public AnimationResourceSelector<T> forAnimation(String animationName, ResourcePath resource) {
            if (animationName == null || animationName.isEmpty() || resource == null) {
                return this;
            }

            this.animationResources.put(animationName, resource);
            return this;
        }

        public AnimationResourceSelector<T> forAnimations(ResourcePath resource, String... animationNames) {
            if (resource == null || animationNames == null) {
                return this;
            }

            for (String animationName : animationNames) {
                this.forAnimation(animationName, resource);
            }

            return this;
        }

        public Function<CreatureState<T>, ResourcePath> build() {
            return state -> {
                String currentAnimation = state.getCurrentAnimation(this.animatorName);
                ResourcePath resource = this.animationResources.get(currentAnimation);

                if (resource != null) {
                    return resource;
                }

                return this.fallback;
            };
        }
    }

    public static class MaskLayer<T extends CustomCreature> {
        private final Function<CreatureState<T>, ResourcePath> mask;
        private final Function<CreatureState<T>, Integer> color;
        private final boolean legacyDefault;

        private MaskLayer(Function<CreatureState<T>, ResourcePath> mask, Function<CreatureState<T>, Integer> color, boolean legacyDefault) {
            this.mask = mask;
            this.color = color;
            this.legacyDefault = legacyDefault;
        }

        public static <T extends CustomCreature> MaskLayer<T> of(Function<CreatureState<T>, ResourcePath> mask, Function<CreatureState<T>, Integer> color) {
            return new MaskLayer<>(mask, color, false);
        }

        public static <T extends CustomCreature> MaskLayer<T> legacyDefault(Function<CreatureState<T>, ResourcePath> mask) {
            return new MaskLayer<>(mask, state -> rgba(0, 255, 0, 255), true);
        }

        public Function<CreatureState<T>, ResourcePath> getMask() {
            return mask;
        }

        public Function<CreatureState<T>, Integer> getColor() {
            return color;
        }
    }
}
