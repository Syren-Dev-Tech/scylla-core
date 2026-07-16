package com.github.syren_dev_tech.scylla.blocks.brushable.types;

import org.jspecify.annotations.Nullable;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomBrushableBlock extends BrushableBlock {

    public static final MapCodec<CustomBrushableBlock> CUSTOM_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("turns_into").forGetter(CustomBrushableBlock::getTurnsInto), BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("brush_sound").forGetter(CustomBrushableBlock::getBrushSound), BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("brush_comleted_sound").forGetter(CustomBrushableBlock::getBrushCompletedSound), propertiesCodec()).apply(instance, CustomBrushableBlock::new));

    public static final int TICK_DELAY = 2;

    public CustomBrushableBlock(Block turnsInto, SoundEvent brushSound, SoundEvent brushCompletedSound, Properties properties) {
        super(turnsInto, brushSound, brushCompletedSound, properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onPlace(BlockState unused1, Level level, BlockPos blockPos, BlockState unused2, boolean unused3) {
        level.scheduleTick(blockPos, this, 2);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState nextBlockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos nextBlockPos) {
        levelAccessor.scheduleTick(blockPos, this, 2);

        return blockState;
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockentity = serverLevel.getBlockEntity(blockPos);
        if (blockentity instanceof BrushableBlockEntity brushableblockentity) {
            brushableblockentity.checkReset();
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BrushableBlockEntity(blockPos, blockState);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MapCodec<BrushableBlock> codec() {
        return (MapCodec<BrushableBlock>) (MapCodec<?>) CUSTOM_CODEC;
    }
}
