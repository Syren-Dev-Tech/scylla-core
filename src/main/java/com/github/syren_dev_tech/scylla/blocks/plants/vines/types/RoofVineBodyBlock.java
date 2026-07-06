package com.github.syren_dev_tech.scylla.blocks.plants.vines.types;

import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeepingVinesPlantBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RoofVineBodyBlock extends WeepingVinesPlantBlock {

    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    private BlockDefinition<RoofVineHeadBlock> headBlock;

    public RoofVineBodyBlock(Properties properties, BlockDefinition<RoofVineHeadBlock> headBlock) {
        super(properties);
        this.headBlock = headBlock;
    }

    public RoofVineBodyBlock(Properties properties) {
        super(properties);
    }

    public void setHeadBlock(BlockDefinition<RoofVineHeadBlock> headBlock) {
        this.headBlock = headBlock;
    }

    @Override
    protected RoofVineHeadBlock getHeadBlock() {
        return this.headBlock.registry.get();
    }
}
