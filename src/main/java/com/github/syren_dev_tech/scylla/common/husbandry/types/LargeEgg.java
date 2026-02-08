package com.github.syren_dev_tech.scylla.common.husbandry.types;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LargeEgg<T extends Animal> extends DragonEggBlock {

    private final LargeEggData<T> eggData;

    @Override
    public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        // No-op
    }

    public LargeEgg(Properties properties, LargeEggData<T> eggData) {
        super(properties);
        this.eggData = eggData;
    }

    public LargeEggData<T> getEggData() {
        return eggData;
    }
}
