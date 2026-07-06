package com.github.syren_dev_tech.scylla.husbandry.types;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;
import net.minecraft.world.level.gameevent.GameEvent.Context;


public class SmallEgg<T extends Mob> extends Block {

    private final SmallEggData<T> eggData;
    private static final ThreadLocal<SmallEggData<?>> CONSTRUCTION_DATA = new ThreadLocal<>();

    private SmallEgg(Properties properties) {
        super(properties);

        @SuppressWarnings("unchecked")
        SmallEggData<T> data = (SmallEggData<T>) CONSTRUCTION_DATA.get();
        this.eggData = data;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.eggData.getHatch(), 0).setValue(this.eggData.getEggs(), this.eggData.getMinEggs()));
    }

    public static <T extends Mob> SmallEgg<T> create(Properties properties, SmallEggData<T> eggData) {
        CONSTRUCTION_DATA.set(eggData);
        try {
            return new SmallEgg<>(properties);
        } finally {
            CONSTRUCTION_DATA.remove();
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            this.destroyEgg(level, state, pos, entity, 100);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        this.destroyEgg(level, state, pos, entity, 3);
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private void destroyEgg(Level level, BlockState state, BlockPos pos, Entity entity, int chance) {
        if (this.canDestroyEgg(level, entity) && !level.isClientSide && level.random.nextInt(chance) == 0 && state.is(Blocks.TURTLE_EGG)) {
            this.decreaseEggs(level, pos, state);
        }
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        level.playSound((Player) null, pos, this.eggData.getBreakSound(), SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        int i = (Integer) state.getValue(this.eggData.getEggs());
        if (i <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, (BlockState) state.setValue(this.eggData.getEggs(), i - 1), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, Context.of(state));
            level.levelEvent(2001, pos, Block.getId(state));
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.shouldUpdateHatchLevel(level) && onNestingBlock(level, pos)) {
            int i = (Integer) state.getValue(this.eggData.getHatch());

            if (i < 2) {
                level.playSound((Player) null, pos, this.eggData.getCrackSound(), SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.setBlock(pos, (BlockState) state.setValue(this.eggData.getHatch(), i + 1), 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(state));
            } else {
                level.playSound((Player) null, pos, this.eggData.getHatchSound(), SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.removeBlock(pos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, Context.of(state));

                for (int j = 0; j < (Integer) state.getValue(this.eggData.getEggs()); ++j) {
                    level.levelEvent(2001, pos, Block.getId(state));
                    final int eggIndex = j;

                    this.eggData.spawn(level, (T newCreature) -> newCreature.moveTo(pos.getX() + 0.3 + eggIndex * 0.2, pos.getY(), pos.getZ() + 0.3, 0.0F, 0.0F));
                }
            }
        }
    }

    public boolean onNestingBlock(BlockGetter level, BlockPos pos) {
        return this.eggData.isNestingBlock(level.getBlockState(pos.below()).getBlock());
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (onNestingBlock(level, pos) && !level.isClientSide) {
            level.levelEvent(2012, pos, 15);
        }
    }

    private boolean shouldUpdateHatchLevel(Level level) {
        float f = level.getTimeOfDay(1.0F);
        return f < 0.69 && f > 0.65 || level.random.nextInt(500) == 0;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, te, stack);
        this.decreaseEggs(level, pos, state);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return !useContext.isSecondaryUseActive() && useContext.getItemInHand().is(this.asItem()) && (Integer) state.getValue(this.eggData.getEggs()) < 4 || super.canBeReplaced(state, useContext);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());

        return blockstate.is(this) ? (BlockState) blockstate.setValue(this.eggData.getEggs(), Math.min(this.eggData.getMaxEggs(), (Integer) blockstate.getValue(this.eggData.getEggs()) + this.eggData.getMinEggs())) : super.getStateForPlacement(context);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.eggData.getShape((Integer) state.getValue(this.eggData.getEggs()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(this.eggData.getHatch(), this.eggData.getEggs());
    }

    private boolean canDestroyEgg(Level level, Entity entity) {
        // Do not allow instance of self destroy the egg
        if (entity instanceof Mob mob && this.eggData.getEntityType().equals(mob.getType())) {
            return false;
        }

        return entity instanceof LivingEntity && entity instanceof Player || EventHooks.canEntityGrief(level, entity);
    }
}
