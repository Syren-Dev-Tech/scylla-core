package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CustomCreature extends PathfinderMob implements GeoEntity { // NOSONAR - Ignore parent class limit

    private final CreatureBuilder<CustomCreature> builder;
    private final AnimatableInstanceCache cache;
    private final CreatureState<? extends CustomCreature> state;

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

    public <T extends CustomCreature> CreatureState<T> getState() {
        return (CreatureState<T>) this.state;
    }

    public CreatureBuilder<CustomCreature> getBuilder() {
        return builder;
    }

    public void requestAnimation(String animatorName, String animation, boolean loop) {
        this.getState().setForcedAnimation(animatorName, animation, loop);
    }

    public void clearRequestedAnimation(String animatorName) {
        this.getState().clearForcedAnimation(animatorName);
    }

    public void clearAllRequestedAnimations() {
        this.getState().clearAllForcedAnimations();
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
}
