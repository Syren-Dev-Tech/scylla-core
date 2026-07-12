package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BrickDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BrickBlockSet {

    public static BrickDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new BrickDefinition(register, name, creativeTab);
    }

    public static BrickDefinition create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return new BrickDefinition(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static BrickDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return new BrickDefinition(register, name, properties, creativeTab);
    }

    private BrickBlockSet() {
        // Prevent instantiation
    }
}
