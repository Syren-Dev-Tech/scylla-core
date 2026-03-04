package com.github.syren_dev_tech.scylla.common.blocks.decoration.storage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Chests {

    public static final BlockDefinition<ChestBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.CHEST), BlockEntityType.CHEST);
    }

    public static final BlockDefinition<ChestBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.CHEST), BlockEntityType.CHEST, creativeTab);
    }

    public static final BlockDefinition<ChestBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, BlockEntityType.CHEST);
    }

    public static final BlockDefinition<ChestBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, BlockEntityType.CHEST, creativeTab);
    }

    public static final <T extends ChestBlockEntity> BlockDefinition<ChestBlock> create(ModRegister register, String name, Properties properties, BlockEntityType<T> blockEntityType) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChestBlock(properties, () -> blockEntityType)));
    }

    public static final <T extends ChestBlockEntity> BlockDefinition<ChestBlock> create(ModRegister register, String name, Properties properties, BlockEntityType<T> blockEntityType, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChestBlock(properties, () -> blockEntityType)), creativeTab);
    }

    private Chests() {
        // Prevent instantiation
    }
}
