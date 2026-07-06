package com.github.syren_dev_tech.scylla.blocks.decoration.crafting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Stonecutters {

    public static BlockDefinition<StonecutterBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONECUTTER));
    }

    public static BlockDefinition<StonecutterBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONECUTTER), creativeTab);
    }

    public static BlockDefinition<StonecutterBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StonecutterBlock(properties)));
    }

    public static BlockDefinition<StonecutterBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StonecutterBlock(properties)), creativeTab);
    }

    private Stonecutters() {
        // Prevent instantiation
    }
}
