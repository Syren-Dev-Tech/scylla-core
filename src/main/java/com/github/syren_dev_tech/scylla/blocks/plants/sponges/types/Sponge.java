package com.github.syren_dev_tech.scylla.blocks.plants.sponges.types;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SpongeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class Sponge extends SpongeBlock {
    private static final Direction[] ALL_DIRECTIONS = Direction.values();
    private Block[] seaFoliage = new Block[] {Blocks.KELP, Blocks.KELP_PLANT, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS};
    private Block wetSponge = Blocks.WET_SPONGE;
    private Block liquid = Blocks.WATER;

    public Sponge(Properties properties) {
        super(properties);
    }

    public Sponge setWetSponge(Block wetSponge) {
        this.wetSponge = wetSponge;

        return this;
    }

    public Sponge setSeaFoliage(Block[] seaFoliage) {
        this.seaFoliage = seaFoliage;

        return this;
    }

    public Sponge setLiquid(Block liquid) {
        this.liquid = liquid;

        return this;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            this.tryAbsorbWater(level, pos);
        }

    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        this.tryAbsorbWater(level, pos);
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }

    @Override
    protected void tryAbsorbWater(Level level, BlockPos pos) {
        if (this.removeWaterBreadthFirstSearch(level, pos)) {
            level.setBlock(pos, Blocks.WET_SPONGE.defaultBlockState(), 2);
            level.playSound((Player) null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

    }

    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos blockPos) {
        BlockState spongeState = level.getBlockState(blockPos);

        return BlockPos.breadthFirstTraversal(blockPos, 6, 65, (actionBlockPos, actionBlockPosConsumer) -> {
            Direction[] var2 = ALL_DIRECTIONS;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Direction direction = var2[var4];
                actionBlockPosConsumer.accept(actionBlockPos.relative(direction));
            }

        }, (innerBlockPos) -> {
            if (innerBlockPos.equals(blockPos)) {
                return true;
            } else {
                BlockState blockstate = level.getBlockState(innerBlockPos);
                FluidState fluidstate = level.getFluidState(innerBlockPos);
                if (!spongeState.canBeHydrated(level, blockPos, fluidstate, innerBlockPos)) {
                    return false;
                } else {
                    Block temp = blockstate.getBlock();
                    if (temp instanceof BucketPickup) {
                        BucketPickup bucketpickup = (BucketPickup) temp;
                        if (!bucketpickup.pickupBlock((Player) null, level, innerBlockPos, blockstate).isEmpty()) {
                            return true;
                        }
                    }

                    if (blockstate.getBlock() instanceof LiquidBlock) {
                        level.setBlock(innerBlockPos, Blocks.AIR.defaultBlockState(), 3);
                    } else {
                        if (!blockstate.is(Blocks.KELP) && !blockstate.is(Blocks.KELP_PLANT) && !blockstate.is(Blocks.SEAGRASS) && !blockstate.is(Blocks.TALL_SEAGRASS)) {
                            return false;
                        }

                        BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(innerBlockPos) : null;
                        dropResources(blockstate, level, innerBlockPos, blockentity);
                        level.setBlock(innerBlockPos, Blocks.AIR.defaultBlockState(), 3);
                    }

                    return true;
                }
            }
        }) > 1;
    }
}
