package com.github.syren_dev_tech.scylla.blocks.misc;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Custom path definition to define default state when destroyed (dirt)

public class Paths {

    public static BlockDefinition<DirtPathBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.DIRT_PATH));
    }

    public static BlockDefinition<DirtPathBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DIRT_PATH), creativeTab);
    }

    public static BlockDefinition<DirtPathBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DirtPathBlock(properties)));
    }

    public static BlockDefinition<DirtPathBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DirtPathBlock(properties)), creativeTab);
    }

    private Paths() {
        // Prevent instantiation
    }
}
