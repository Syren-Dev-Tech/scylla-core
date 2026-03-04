package com.github.syren_dev_tech.scylla.common.blocks.plants.vines.types;

import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.WeepingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RoofVineHeadBlock extends WeepingVinesBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private Block bodyBlock;

    public RoofVineHeadBlock(Properties properties, BlockDefinition<Block> bodyBlock) {
        super(properties);
        this.bodyBlock = bodyBlock.registry.get();
    }

    public RoofVineHeadBlock(Properties properties) {
        super(properties);
    }

    public void setBodyBlock(Block bodyBlock) {
        this.bodyBlock = bodyBlock;
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
    }

    protected Block getBodyBlock() {
        return this.bodyBlock;
    }

    protected boolean canGrowInto(BlockState blockState) {
        return NetherVines.isValidGrowthState(blockState);
    }
}
