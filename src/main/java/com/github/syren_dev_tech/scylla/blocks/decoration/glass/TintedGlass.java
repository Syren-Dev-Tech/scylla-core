package com.github.syren_dev_tech.scylla.blocks.decoration.glass;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TintedGlass {

    public static BlockDefinition<TintedGlassBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.TINTED_GLASS));
    }

    public static BlockDefinition<TintedGlassBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.TINTED_GLASS), creativeTab);
    }

    public static BlockDefinition<TintedGlassBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TintedGlassBlock(properties)));
    }

    public static BlockDefinition<TintedGlassBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TintedGlassBlock(properties)), creativeTab);
    }

    private TintedGlass() {
        // Prevent instantiation
    }
}
