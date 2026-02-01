package com.github.syren_dev_tech.scylla.common.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.common.collections.Tuple;
import com.github.syren_dev_tech.scylla.common.mobs.client.TextureRenderer;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.Animator;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public class CreatureBuilder<T extends CustomCreature> {

    private final ModRegister register;
    private final String name;

    private List<Goal> goals = new ArrayList<>();
    private Function<CreatureState<T>, ResourceLocation> textures;
    private TextureRenderer<T> textureRenderer;
    private Map<String, Animator<T>> animators = new HashMap<>();
    private CreatureFactory<T> factory;
    private float shadowSize = 0.5f;
    private boolean rideable = false;

    public CreatureBuilder(String name, ModRegister register, CreatureFactory<T> factory) {
        this.name = name;
        this.register = register;
        this.factory = factory;

        this.textures = t -> new ResourceLocation(register.modId, "textures/entity/" + name + ".png");
    }

    public ModRegister getRegister() {
        return register;
    }

    public CreatureBuilder<T> withTextures(Function<CreatureState<T>, ResourceLocation> textures) {
        this.textures = textures;
        return this;
    }

    public CreatureBuilder<T> withAiGoal(Goal goal) {
        this.goals.add(goal);
        return this;
    }

    public CreatureBuilder<T> withAiGoals(List<Goal> goals) {
        this.goals.addAll(goals);
        return this;
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

    public Function<CreatureState<T>, ResourceLocation> getTextures() {
        return textures;
    }

    public float getShadowSize() {
        return shadowSize;
    }

    public TextureRenderer<T> getTextureRenderer() {
        return textureRenderer;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Map<String, Animator<T>> getAnimators() {
        return animators;
    }

    public boolean isRideable() {
        return rideable;
    }

    public Supplier<EntityType<T>> register() {
        var registry = this.register.mobRegistry.register(this);

        var registrar = new CreatureRegistrar<T>(this, registry, this::createAttributes);
        this.register.mobRegistry.entities.put(this.name, registrar);

        return registry;
    }

    public Animator<T> addAnimator(String name, Function<CreatureState<T>, Tuple<String, Boolean>> handler) {
        Animator<T> animator = new Animator<>(handler);
        this.animators.put(name, animator);

        return animator;
    }

    public interface CreatureFactory<T extends CustomCreature> {
        T create(EntityType<? extends CustomCreature> type, Level world, CreatureBuilder<T> builder);
    }
}
