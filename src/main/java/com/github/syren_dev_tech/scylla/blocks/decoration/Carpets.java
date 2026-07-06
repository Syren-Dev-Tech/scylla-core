package com.github.syren_dev_tech.scylla.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Carpets {

    public static BlockDefinition<CarpetBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CARPET));
    }

    public static BlockDefinition<CarpetBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CARPET), creativeTab);
    }

    public static BlockDefinition<CarpetBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CarpetBlock(properties)));
    }

    public static BlockDefinition<CarpetBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CarpetBlock(properties)), creativeTab);
    }

    private Carpets() {
        // Prevent instantiation
    }
}
