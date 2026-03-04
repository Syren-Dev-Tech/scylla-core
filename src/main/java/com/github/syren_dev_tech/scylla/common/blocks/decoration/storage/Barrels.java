package com.github.syren_dev_tech.scylla.common.blocks.decoration.storage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Barrels {

    public static final BlockDefinition<BarrelBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.BARREL));
    }

    public static final BlockDefinition<BarrelBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.BARREL), creativeTab);
    }

    public static final BlockDefinition<BarrelBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BarrelBlock(properties)));
    }

    public static final BlockDefinition<BarrelBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BarrelBlock(properties)), creativeTab);
    }

    private Barrels() {
        // Prevent instantiation
    }
}
