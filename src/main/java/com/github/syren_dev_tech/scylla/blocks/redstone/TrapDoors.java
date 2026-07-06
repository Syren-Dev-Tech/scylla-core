package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class TrapDoors {

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockSetType.OAK);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Block block) {
        return create(register, name, Properties.ofFullCopy(block), BlockSetType.OAK);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Block block, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(block), BlockSetType.OAK, creativeTab);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, BlockSetType blockSetType) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), blockSetType);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), blockSetType, creativeTab);
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TrapDoorBlock(blockSetType, properties)));
    }

    public static BlockDefinition<TrapDoorBlock> create(ModRegister register, String name, Properties properties, BlockSetType blockSetType, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TrapDoorBlock(blockSetType, properties)), creativeTab);
    }

    private TrapDoors() {
        // Prevent instantiation
    }
}
