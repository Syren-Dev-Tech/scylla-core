package com.github.syren_dev_tech.scylla.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Needs custom implementation...

public class Blossoms {

    public static BlockDefinition<SporeBlossomBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.SPORE_BLOSSOM));
    }

    public static BlockDefinition<SporeBlossomBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.SPORE_BLOSSOM), creativeTab);
    }

    public static BlockDefinition<SporeBlossomBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SporeBlossomBlock(properties)));
    }

    public static BlockDefinition<SporeBlossomBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SporeBlossomBlock(properties)), creativeTab);
    }

    private Blossoms() {
        // Prevent instantiation
    }
}
