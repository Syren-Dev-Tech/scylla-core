package com.github.syren_dev_tech.scylla.blocks.decoration.crystals;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MediumCrystals {

    public static BlockDefinition<AmethystClusterBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER));
    }

    public static BlockDefinition<AmethystClusterBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER), creativeTab);
    }

    public static BlockDefinition<AmethystClusterBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new AmethystClusterBlock(4, 3, properties)));
    }

    public static BlockDefinition<AmethystClusterBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new AmethystClusterBlock(4, 3, properties)), creativeTab);
    }

    private MediumCrystals() {
        // Prevent instantiation
    }
}
