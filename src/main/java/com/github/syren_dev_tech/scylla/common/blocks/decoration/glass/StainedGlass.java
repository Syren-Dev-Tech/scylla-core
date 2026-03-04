package com.github.syren_dev_tech.scylla.common.blocks.decoration.glass;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StainedGlass {

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.IRON_BARS));
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.GLASS_PANE), creativeTab);
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, DyeColor color) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS), color);
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS), color, creativeTab);
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, DyeColor.WHITE);
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, DyeColor.WHITE, creativeTab);
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, Properties properties, DyeColor color) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StainedGlassBlock(color, properties)));
    }

    public static final BlockDefinition<StainedGlassBlock> create(ModRegister register, String name, Properties properties, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new StainedGlassBlock(color, properties)), creativeTab);
    }

    private StainedGlass() {
        // Prevent instantiation
    }
}
