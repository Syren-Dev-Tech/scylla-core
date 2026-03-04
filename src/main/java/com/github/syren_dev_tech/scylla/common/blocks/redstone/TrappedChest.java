package com.github.syren_dev_tech.scylla.common.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TrappedChest {

    public static final BlockDefinition<TrappedChestBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.TRAPPED_CHEST));
    }

    public static final BlockDefinition<TrappedChestBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.TRAPPED_CHEST), creativeTab);
    }

    public static final BlockDefinition<TrappedChestBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TrappedChestBlock(properties)));
    }

    public static final BlockDefinition<TrappedChestBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TrappedChestBlock(properties)), creativeTab);
    }

    private TrappedChest() {
        // Prevent instantiation
    }
}
