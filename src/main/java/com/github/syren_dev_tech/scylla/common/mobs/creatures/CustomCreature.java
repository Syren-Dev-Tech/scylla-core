package com.github.syren_dev_tech.scylla.common.mobs.creatures;

import com.github.syren_dev_tech.scylla.common.mobs.CreatureBuilder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        builder.getAnimators().forEach((name, animator) -> controllers.add(new AnimationController<>(this, name, 10, animator::apply)));
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
}
