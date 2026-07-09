package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CustomCreature extends PathfinderMob implements GeoEntity { // NOSONAR - Ignore parent class limit

    private static final EntityDataAccessor<String> FORCED_ANIMATIONS = SynchedEntityData.defineId(CustomCreature.class, EntityDataSerializers.STRING);

    private final CreatureBuilder<CustomCreature> builder;
    private final AnimatableInstanceCache cache;
    private final CreatureState<CustomCreature> state;

    public CustomCreature(EntityType<? extends PathfinderMob> type, Level worldIn, CreatureBuilder<CustomCreature> builder) {
        super(type, worldIn);

        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.builder = builder;
        this.state = new CreatureState<>(builder);

        this.registerGoalsFromBuilder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomCreature that = (CustomCreature) obj;

        return this.getUUID().equals(that.getUUID());
    }

    @Override
    public int hashCode() {
        return this.getUUID().hashCode();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public CreatureState<CustomCreature> getState() {
        return this.state;
    }

    public CreatureBuilder<CustomCreature> getBuilder() {
        return builder;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FORCED_ANIMATIONS, "");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (FORCED_ANIMATIONS.equals(key) && this.level().isClientSide) {
            this.applySyncedForcedAnimations();
        }
    }

    public void requestAnimation(String animatorName, String animation, boolean loop) {
        this.getState().setForcedAnimation(animatorName, animation, loop);
        this.syncForcedAnimations();
    }

    public boolean requestAnimation(String animatorName, String animation, boolean loop, Object owner) {
        boolean updated = this.getState().setForcedAnimation(animatorName, animation, loop, owner);

        if (updated) {
            this.syncForcedAnimations();
        }

        return updated;
    }

    public void claimAnimation(String animatorName, Object owner) {
        this.getState().claimForcedAnimationOwner(animatorName, owner);
    }

    public void clearRequestedAnimation(String animatorName) {
        this.getState().clearForcedAnimation(animatorName);
        this.syncForcedAnimations();
    }

    public boolean clearRequestedAnimation(String animatorName, Object owner) {
        boolean updated = this.getState().clearForcedAnimation(animatorName, owner);

        if (updated) {
            this.syncForcedAnimations();
        }

        return updated;
    }

    public void clearAllRequestedAnimations() {
        this.getState().clearAllForcedAnimations();
        this.syncForcedAnimations();
    }

    private void registerGoalsFromBuilder() {
        builder.getGoals().forEach(goalFunc -> {
            var goal = goalFunc.apply(this);
            this.goalSelector.addGoal(goal.getWeight(), goal);
        });
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        builder.getAnimators().forEach((name, handler) -> {
            Animator<CustomCreature> animator = new Animator<>(name, handler);
            this.getState().setAnimator(name, animator);
            controllers.add(new AnimationController<>(this, name, 10, animator::apply));
        });
    }

    private void syncForcedAnimations() {
        if (this.level().isClientSide) {
            return;
        }

        this.entityData.set(FORCED_ANIMATIONS, this.serializeForcedAnimations());
    }

    private void applySyncedForcedAnimations() {
        this.getState().replaceForcedAnimations(this.deserializeForcedAnimations(this.entityData.get(FORCED_ANIMATIONS)));
    }

    private String serializeForcedAnimations() {
        StringBuilder encoded = new StringBuilder();

        for (Map.Entry<String, AnimationDefinition> entry : this.getState().getForcedAnimationsSnapshot().entrySet()) {
            AnimationDefinition definition = entry.getValue();

            if (definition == null || definition.animation() == null || definition.animation().isEmpty()) {
                continue;
            }

            if (!encoded.isEmpty()) {
                encoded.append('\n');
            }

            encoded.append(entry.getKey()).append('\t').append(definition.animation()).append('\t').append(definition.loop() ? '1' : '0');
        }

        return encoded.toString();
    }

    private Map<String, AnimationDefinition> deserializeForcedAnimations(String encoded) {
        Map<String, AnimationDefinition> animations = new HashMap<>();

        if (encoded == null || encoded.isEmpty()) {
            return animations;
        }

        for (String entry : encoded.split("\\n")) {
            if (!entry.isEmpty()) {
                String[] parts = entry.split("\\t", 3);
                if (parts.length >= 3 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                    animations.put(parts[0], new AnimationDefinition(parts[1], "1".equals(parts[2])));
                }
            }
        }

        return animations;
    }

}
