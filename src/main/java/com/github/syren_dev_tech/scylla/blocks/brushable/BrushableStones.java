package com.github.syren_dev_tech.scylla.blocks.brushable;

import com.github.syren_dev_tech.scylla.blocks.brushable.types.CustomBrushableBlock;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BrushableStones {

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, BlockDefinition<Block> dustedBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE), dustedBlock);
    }

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, BlockDefinition<Block> dustedBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE), dustedBlock, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED, creativeTab);
    }

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> dustedBlock) {
        return create(register, name, properties, dustedBlock, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED);
    }

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> dustedBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, dustedBlock, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED, creativeTab);
    }

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> dustedBlock, SoundEvent dustingSound, SoundEvent dustingCompletedSound) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomBrushableBlock(dustedBlock.registry.get(), properties, dustingSound, dustingCompletedSound)));
    }

    public static BlockDefinition<CustomBrushableBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> dustedBlock, SoundEvent dustingSound, SoundEvent dustingCompletedSound, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomBrushableBlock(dustedBlock.registry.get(), properties, dustingSound, dustingCompletedSound)), creativeTab);
    }

    private BrushableStones() {
        // Prevent instantiation
    }
}
