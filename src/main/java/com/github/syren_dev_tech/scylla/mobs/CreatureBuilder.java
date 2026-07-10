package com.github.syren_dev_tech.scylla.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition;
import com.github.syren_dev_tech.scylla.mobs.ai.goals.AnimatedGoal;
import com.github.syren_dev_tech.scylla.mobs.client.TextureDefinition;
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

    private final CreatureFactory<T> factory;
    private final List<Function<T, AIGoal>> goals = new ArrayList<>();
    private final List<MaskLayer<T>> maskLayers = new ArrayList<>();
    private final Map<String, AnimationHandler<T>> animators = new HashMap<>();
    private final Map<String, TextureDefinition> textures = new HashMap<>();
    private final ModRegister register;
    private final TextureDefinition defaultTexture;
    private final String name;

    private boolean rideable = false;
    private float shadowSize = 0.5f;

    public CreatureBuilder(String name, ModRegister register, CreatureFactory<T> factory) {
        this.name = name;
        this.register = register;
        this.factory = factory;
        this.defaultTexture = new TextureDefinition(register, name);
    }

    public ModRegister getRegister() {
        return register;
    }

    public final CreatureBuilder<T> withTexture(String textureName, String... animations) {
        if (textureName == null || textureName.isEmpty()) {
            return this;
        }

        if (animations == null || animations.length == 0) {
            this.defaultTexture.setTexture(new TextureDefinition(register, textureName).getTexture());
            return this;
        }

        TextureDefinition textureDefinition = new TextureDefinition(register, textureName);
        for (String animation : animations) {
            if (animation != null && !animation.isEmpty()) {
                // If the definition already exists, set the texture
                this.textures.computeIfAbsent(animation, anim -> textureDefinition).setTexture(textureDefinition.getTexture());
            }
        }

        return this;
    }

    public CreatureBuilder<T> withMask(ResourcePath maskName, String... animations) {
        if (maskName == null) {
            return this;
        }

        if (animations == null || animations.length == 0) {
            this.defaultTexture.setMask(maskName);
            return this;
        }

        for (String animation : animations) {
            if (animation != null && !animation.isEmpty()) {
                this.textures.computeIfAbsent(animation, anim -> new TextureDefinition(this.defaultTexture.getTexture(), null)).setMask(maskName);
            }
        }

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
        return this.withAiGoal(this.createGoalDefinition(goal, animatorName, movingAnimation, idleAnimation));
    }

    public CreatureBuilder<T> withAiGoal(Function<T, AIGoal> goal, String animatorName, String... animations) {
        return this.withAiGoal(this.createGoalDefinition(goal, animatorName, animations));
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals) {
        this.goals.addAll(goals);
        return this;
    }

    public CreatureBuilder<T> withAiGoalsFromDefinitions(List<AIGoalDefinition<T>> goalDefinitions) {
        goalDefinitions.forEach(this::withAiGoal);
        return this;
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String movingAnimation, String idleAnimation) {
        goals.forEach(goal -> this.withAiGoal(goal, animatorName, movingAnimation, idleAnimation));
        return this;
    }

    public CreatureBuilder<T> withAiGoals(List<Function<T, AIGoal>> goals, String animatorName, String... animations) {
        goals.forEach(goal -> this.withAiGoal(goal, animatorName, animations));
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

        AnimatedGoal<T> animatedGoal = new AnimatedGoal<>(entity, baseGoal);
        animatedGoal.withAnimation(goalDefinition.getAnimatorName(), goalDefinition.getAnimations());
        return animatedGoal;
    }

    private AIGoalDefinition<T> createGoalDefinition(Function<T, AIGoal> goal, String animatorName, String movingAnimation, String idleAnimation) {
        return AIGoalDefinition.<T>define(goal).withAnimator(animatorName, movingAnimation, idleAnimation).build();
    }

    private AIGoalDefinition<T> createGoalDefinition(Function<T, AIGoal> goal, String animatorName, String... animations) {
        return AIGoalDefinition.<T>define(goal).withAnimator(animatorName, animations).build();
    }

    private void ensureGoalAnimator(AIGoalDefinition<T> goalDefinition) {
        if (!goalDefinition.hasAnimatorBinding()) {
            return;
        }

        this.animators.computeIfAbsent(goalDefinition.getAnimatorName(), animator -> AnimationHandler.fromStateHandler(state -> null));
    }

    public CreatureBuilder<T> withShadowSize(float shadowSize) {
        this.shadowSize = shadowSize;
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

    public List<MaskLayer<T>> getMaskLayers() {
        return maskLayers;
    }

    public float getShadowSize() {
        return shadowSize;
    }

    public List<Function<T, AIGoal>> getGoals() {
        return goals;
    }

    public Map<String, AnimationHandler<T>> getAnimators() {
        return animators;
    }

    public Map<String, TextureDefinition> getTextures() {
        return textures;
    }

    public TextureDefinition getDefaultTexture() {
        return defaultTexture;
    }

    public TextureDefinition getTextureForAnimation(String animation) {
        if (animation == null || animation.isEmpty()) {
            return this.defaultTexture;
        }

        TextureDefinition definition = this.textures.get(animation);
        if (definition != null) {
            return definition;
        }

        return this.defaultTexture;
    }

    public TextureDefinition getTextureForState(CreatureState<T> state) {
        if (state == null) {
            return this.defaultTexture;
        }

        for (String animation : state.getCurrentAnimations()) {
            TextureDefinition definition = this.getTextureForAnimation(animation);
            if (definition != this.defaultTexture) {
                return definition;
            }
        }

        return this.defaultTexture;
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
        return this.addAnimator(name, AnimationHandler.fromStateHandler(handler));
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

    public static AnimationResource animationResource(String animationName, ResourcePath resource) {
        return new AnimationResource(animationName, resource);
    }

    public static record AnimationResource(String animationName, ResourcePath resource) {
    }

    @SafeVarargs
    public final CreatureBuilder<T> withTexture(ResourcePath resource, String... animations) {
        if (resource == null) {
            return this;
        }

        if (animations == null || animations.length == 0) {
            this.defaultTexture.setTexture(resource);
            return this;
        }

        TextureDefinition definition = new TextureDefinition(register, "").setTexture(resource);
        for (String animation : animations) {
            if (animation != null && !animation.isEmpty()) {
                this.textures.computeIfAbsent(animation, anim -> definition).setTexture(resource);
            }
        }

        return this;
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

        public boolean isLegacyDefault() {
            return this.legacyDefault;
        }
    }
}
