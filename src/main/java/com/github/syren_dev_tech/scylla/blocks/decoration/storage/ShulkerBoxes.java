package com.github.syren_dev_tech.scylla.blocks.decoration.storage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ShulkerBoxes {

    public static BlockDefinition<ShulkerBoxBlock> create(ModRegister register, String name, DyeColor color) {
        return create(register, name, color, Properties.ofFullCopy(Blocks.WHITE_SHULKER_BOX));
    }

    public static BlockDefinition<ShulkerBoxBlock> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, color, Properties.ofFullCopy(Blocks.WHITE_SHULKER_BOX), creativeTab);
    }

    public static BlockDefinition<ShulkerBoxBlock> create(ModRegister register, String name, DyeColor color, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ShulkerBoxBlock(color, properties)));
    }

    public static BlockDefinition<ShulkerBoxBlock> create(ModRegister register, String name, DyeColor color, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ShulkerBoxBlock(color, properties)), creativeTab);
    }

    private ShulkerBoxes() {
        // Prevent instantiation
    }
}
