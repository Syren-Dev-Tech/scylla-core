package com.github.syren_dev_tech.scylla.common.husbandry.types;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;

public class MediumEgg<T extends Mob> extends Block {

    private final MediumEggData<T> eggData;
    private static final ThreadLocal<MediumEggData<?>> CONSTRUCTION_DATA = new ThreadLocal<>();

    private MediumEgg(Properties properties) {
        super(properties);
        // eggData is provided via CONSTRUCTION_DATA during construction
        @SuppressWarnings("unchecked")
        MediumEggData<T> data = (MediumEggData<T>) CONSTRUCTION_DATA.get();
        this.eggData = data;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.eggData.getHatch(), 0));
    }

    public static <T extends Mob> MediumEgg<T> create(Properties properties, MediumEggData<T> eggData) {
        CONSTRUCTION_DATA.set(eggData);
        try {
            return new MediumEgg<>(properties);
        } finally {
            CONSTRUCTION_DATA.remove();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        @SuppressWarnings("unchecked")
        MediumEggData<T> data = this.eggData != null ? this.eggData : (MediumEggData<T>) CONSTRUCTION_DATA.get();
        builder.add(data.getHatch());
    }

    @Override
    public MapCodec<MediumEgg<T>> codec() {
        return simpleCodec(properties -> MediumEgg.create(properties, this.eggData));
    }

    public int getHatchLevel(BlockState state) {
        return (Integer) state.getValue(this.eggData.getHatch());
    }

    private boolean isReadyToHatch(BlockState state) {
        return this.getHatchLevel(state) == this.eggData.getMaxHatchLevel();
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randSource) { // NOSONAR - Ignore deprecation warning
        if (!this.isReadyToHatch(blockState)) {
            serverLevel.playSound((Player) null, blockPos, this.eggData.getCrackSound(), SoundSource.BLOCKS, 0.7F, 0.9F + randSource.nextFloat() * 0.2F);
            serverLevel.setBlock(blockPos, blockState.setValue(this.eggData.getHatch(), Integer.valueOf(this.getHatchLevel(blockState) + 1)), 2);
        } else {
            serverLevel.playSound((Player) null, blockPos, this.eggData.getHatchSound(), SoundSource.BLOCKS, 0.7F, 0.9F + randSource.nextFloat() * 0.2F);
            serverLevel.destroyBlock(blockPos, false);

            this.eggData.spawn(serverLevel, (T newCreature) -> {
                Vec3 vec3 = blockPos.getCenter();
                newCreature.moveTo(vec3.x(), vec3.y(), vec3.z(), Mth.wrapDegrees(serverLevel.random.nextFloat() * 360.0F), 0.0F);
            });
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        boolean flag = hatchBoost(level, pos);
        if (!level.isClientSide() && flag) {
            level.levelEvent(3009, pos, 0);
        }

        int i = flag ? this.eggData.getBoostedHatchTimeTicks() : this.eggData.getRegularHatchTimeTicks();
        int j = i / 3;

        level.gameEvent(GameEvent.BLOCK_PLACE, pos, Context.of(state));
        level.scheduleTick(pos, this, j + level.random.nextInt(this.eggData.getRandomHatchOffsetTicks()));
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    protected boolean hatchBoost(BlockGetter level, BlockPos pos) {
        var boost = this.eggData.getBoost();
        if (boost == null)
            return false;

        return level.getBlockState(pos.below()).is(boost);
    }
}
