package com.github.syren_dev_tech.scylla.blocks.decoration.storage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EnderChests {

    public static BlockDefinition<EnderChestBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.ENDER_CHEST));
    }

    public static BlockDefinition<EnderChestBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.ENDER_CHEST), creativeTab);
    }

    public static BlockDefinition<EnderChestBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EnderChestBlock(properties)));
    }

    public static BlockDefinition<EnderChestBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EnderChestBlock(properties)), creativeTab);
    }

    private EnderChests() {
        // Prevent instantiation
    }
}
