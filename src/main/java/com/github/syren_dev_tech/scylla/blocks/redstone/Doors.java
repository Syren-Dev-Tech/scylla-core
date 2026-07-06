package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class Doors {

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name) {
        return create(register, name, Blocks.OAK_DOOR, BlockSetType.OAK);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Blocks.OAK_DOOR, BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockSetType.OAK);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, BlockSetType blockSetType) {
        return create(register, name, Blocks.OAK_DOOR, blockSetType);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Blocks.OAK_DOOR, blockSetType, creativeTab);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Block sourceBlock) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), BlockSetType.OAK);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Block sourceBlock, BlockSetType blockSetType) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), blockSetType);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Block sourceBlock, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), blockSetType, creativeTab);
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DoorBlock(blockSetType, properties.noOcclusion())));
    }

    public static BlockDefinition<DoorBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DoorBlock(blockSetType, properties.noOcclusion())), creativeTab);
    }

    private Doors() {
        // Prevent instantiation
    }
}
