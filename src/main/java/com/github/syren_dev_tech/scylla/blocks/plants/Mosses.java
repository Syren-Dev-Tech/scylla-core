package com.github.syren_dev_tech.scylla.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Mosses {

    public static BlockDefinition<MossBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.MOSS_BLOCK));
    }

    public static BlockDefinition<MossBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.MOSS_BLOCK), creativeTab);
    }

    public static BlockDefinition<MossBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new MossBlock(properties)));
    }

    public static BlockDefinition<MossBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new MossBlock(properties)), creativeTab);
    }

    private Mosses() {
        // Prevent instantiation
    }
}
