package com.github.syren_dev_tech.scylla.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Needs custom implementation...

public class TreeBushes {

    public static BlockDefinition<AzaleaBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.AZALEA));
    }

    public static BlockDefinition<AzaleaBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.AZALEA), creativeTab);
    }

    public static BlockDefinition<AzaleaBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new AzaleaBlock(properties)));
    }

    public static BlockDefinition<AzaleaBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new AzaleaBlock(properties)), creativeTab);
    }

    private TreeBushes() {
        // Prevent instantiation
    }
}
