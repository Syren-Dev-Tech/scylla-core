package com.github.syren_dev_tech.scylla.common.husbandry.types;

import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public class MediumEggData<T extends Mob> extends EggData<T> {
    public MediumEggData(Supplier<EntityType<T>> entityType) {
        super(entityType);

        this.setHatchSound(SoundEvents.SNIFFER_EGG_HATCH);
        this.setCrackSound(SoundEvents.SNIFFER_EGG_CRACK);
    }
}
