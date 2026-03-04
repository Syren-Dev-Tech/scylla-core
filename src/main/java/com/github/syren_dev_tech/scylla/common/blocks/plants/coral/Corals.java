package com.github.syren_dev_tech.scylla.common.blocks.plants.coral;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Corals {

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK), BlockDefinition.of(() -> Blocks.DEAD_FIRE_CORAL_BLOCK));
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK), BlockDefinition.of(() -> Blocks.DEAD_FIRE_CORAL_BLOCK), creativeTab);
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockDefinition.of(() -> Blocks.DEAD_FIRE_CORAL_BLOCK));
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockDefinition.of(() -> Blocks.DEAD_FIRE_CORAL_BLOCK), creativeTab);
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, BlockDefinition<Block> deadBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK), deadBlock);
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, BlockDefinition<Block> deadBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK), deadBlock, creativeTab);
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> deadBlock) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CoralBlock(deadBlock.registry.get(), properties)));
    }

    public static final BlockDefinition<CoralBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> deadBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CoralBlock(deadBlock.registry.get(), properties)), creativeTab);
    }

    private Corals() {
        // Prevent instantiation
    }
}
