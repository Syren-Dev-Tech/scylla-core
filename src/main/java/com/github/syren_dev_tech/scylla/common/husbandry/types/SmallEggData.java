package com.github.syren_dev_tech.scylla.common.husbandry.types;

import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallEggData<T extends Mob> extends EggData<T> {

    private final VoxelShape oneEgg = Block.box(3.0F, 0.0F, 3.0F, 12.0F, 7.0F, 12.0F);
    private final VoxelShape multipleEggs = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 7.0F, 15.0F);

    private int minEggs = 1;
    private int maxEggs = 4;

    private IntegerProperty eggs = IntegerProperty.create("eggs", minEggs, maxEggs);

    private SoundEvent breakSound;

    public SmallEggData(Supplier<EntityType<T>> entityType) {
        super(entityType);

        this.setHatchSound(SoundEvents.TURTLE_EGG_HATCH);
        this.setCrackSound(SoundEvents.TURTLE_EGG_CRACK);
        this.setBreakSound(SoundEvents.TURTLE_EGG_BREAK);
    }

    public SoundEvent getBreakSound() {
        return breakSound;
    }

    public SmallEggData<T> setBreakSound(SoundEvent breakSound) {
        this.breakSound = breakSound;
        return this;
    }

    public int getMinEggs() {
        return minEggs;
    }

    public SmallEggData<T> setMinEggs(int minEggs) {
        this.minEggs = minEggs;
        this.eggs = IntegerProperty.create("eggs", minEggs, maxEggs);
        return this;
    }

    public int getMaxEggs() {
        return maxEggs;
    }

    public SmallEggData<T> setMaxEggs(int maxEggs) {
        this.maxEggs = maxEggs;
        this.eggs = IntegerProperty.create("eggs", minEggs, maxEggs);
        return this;
    }

    public IntegerProperty getEggs() {
        return eggs;
    }

    public VoxelShape getShape(int eggCount) {
        return eggCount > 1 ? multipleEggs : oneEgg;
    }
}
