package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FallingBlocks {

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor) {
        return create(register, name, dustColor, Properties.ofFullCopy(Blocks.SAND));
    }

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, dustColor, Properties.ofFullCopy(Blocks.SAND), creativeTab);
    }

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor, Block sourceBlock) {
        return create(register, name, dustColor, Properties.ofFullCopy(sourceBlock));
    }

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, dustColor, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ColoredFallingBlock(dustColor, properties)));
    }

    public static BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ColorRGBA dustColor, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ColoredFallingBlock(dustColor, properties)), creativeTab);
    }

    private FallingBlocks() {
        // Prevent instantiation
    }
}
