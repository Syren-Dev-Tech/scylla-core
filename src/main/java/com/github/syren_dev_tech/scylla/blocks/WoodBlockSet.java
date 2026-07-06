package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.WoodDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WoodBlockSet {

    public static final WoodDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, creativeTab);
    }

    public static final WoodDefinition create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static final WoodDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, properties, creativeTab);
    }

    private WoodBlockSet() {
        // Prevent instantiation
    }
}
