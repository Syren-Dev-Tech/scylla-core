package com.github.syren_dev_tech.scylla.common.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class Stairs {

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_STAIRS), BlockDefinition.of(() -> Blocks.STONE));
    }

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_STAIRS), BlockDefinition.of(() -> Blocks.STONE), creativeTab);
    }

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name, BlockDefinition<Block> sourceBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_STAIRS), sourceBlock);
    }

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name, BlockDefinition<Block> sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_STAIRS), sourceBlock, creativeTab);
    }

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> sourceBlock) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StairBlock(sourceBlock.registry.get().defaultBlockState(), properties)));
    }

    public static final BlockDefinition<StairBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StairBlock(sourceBlock.registry.get().defaultBlockState(), properties)), creativeTab);
    }

    private Stairs() {
        // Prevent instantiation
    }
}
