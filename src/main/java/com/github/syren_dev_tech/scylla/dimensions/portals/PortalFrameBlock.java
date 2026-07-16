package com.github.syren_dev_tech.scylla.dimensions.portals;

import com.mojang.serialization.MapCodec;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PortalFrameBlock extends Block {

    public static final BooleanProperty HAS_KEY = BooleanProperty.create("has_key");
    protected static final VoxelShape FULL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    private final Item keyItem;

    public PortalFrameBlock(Properties properties) {
        this(properties, null);
    }

    public PortalFrameBlock(Properties properties, Item keyItem) {
        super(properties);
        this.keyItem = keyItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_KEY, Boolean.valueOf(false)));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return simpleCodec((properties) -> new PortalFrameBlock(properties, this.keyItem));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) { // NOSONAR - Ignore deprecation warning
        return FULL_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HAS_KEY, Boolean.valueOf(false));
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) { // NOSONAR - Ignore deprecation warning
        return this.keyItem != null;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) { // NOSONAR - Ignore deprecation warning
        return state.getValue(HAS_KEY).booleanValue() ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_KEY);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, net.minecraft.world.InteractionHand hand, BlockHitResult hitResult) {
        if (this.keyItem == null || !stack.is(this.keyItem) || state.getValue(HAS_KEY).booleanValue()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        level.setBlock(pos, state.setValue(HAS_KEY, Boolean.valueOf(true)), 3);
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
