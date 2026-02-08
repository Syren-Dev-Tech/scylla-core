package com.github.syren_dev_tech.scylla.common.blocks.redstone;

import java.util.function.Supplier;

import com.github.syren_dev_tech.scylla.common.registry.ModRegister;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PressurePlates {

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE), BlockSetType.STONE);
    }

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE), BlockSetType.STONE, creativeTab);
    }

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockSetType.STONE);
    }

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockSetType.STONE, creativeTab);
    }

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType) {
        return register.blockRegistry.register(name, () -> new PressurePlateBlock(blockSetType, properties));
    }

    public static final Supplier<PressurePlateBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, () -> new PressurePlateBlock(blockSetType, properties), creativeTab);
    }

    private PressurePlates() {
        // Prevent instantiation
    }
}
