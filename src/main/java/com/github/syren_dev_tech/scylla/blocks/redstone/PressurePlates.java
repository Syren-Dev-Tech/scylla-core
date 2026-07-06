package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PressurePlates {

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE), BlockSetType.STONE);
    }

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE), BlockSetType.STONE, creativeTab);
    }

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockSetType.STONE);
    }

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockSetType.STONE, creativeTab);
    }

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PressurePlateBlock(blockSetType, properties)));
    }

    public static BlockDefinition<PressurePlateBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PressurePlateBlock(blockSetType, properties)), creativeTab);
    }

    private PressurePlates() {
        // Prevent instantiation
    }
}
