package com.github.syren_dev_tech.scylla.blocks.decoration.crafting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Grindstones {

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.GRINDSTONE));
    }

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.GRINDSTONE), creativeTab);
    }

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name, Block sourceBlock) {
        return create(register, name, Properties.ofFullCopy(sourceBlock));
    }

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new GrindstoneBlock(properties)));
    }

    public static BlockDefinition<GrindstoneBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new GrindstoneBlock(properties)), creativeTab);
    }

    private Grindstones() {
        // Prevent instantiation
    }
}
