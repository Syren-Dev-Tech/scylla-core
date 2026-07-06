package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class WallBlocks {

    public static BlockDefinition<WallBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.COBBLESTONE_WALL));
    }

    public static BlockDefinition<WallBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.COBBLESTONE_WALL), creativeTab);
    }

    public static BlockDefinition<WallBlock> create(ModRegister register, String name, Block sourceBlock) {
        return create(register, name, Properties.ofFullCopy(sourceBlock));
    }

    public static BlockDefinition<WallBlock> create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static BlockDefinition<WallBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallBlock(properties)));
    }

    public static BlockDefinition<WallBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallBlock(properties)), creativeTab);
    }

    private WallBlocks() {
        // Prevent instantiation
    }
}
