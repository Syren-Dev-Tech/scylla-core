package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.StoneDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StoneBlockSet {

    public static StoneDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new StoneDefinition(register, name, creativeTab);
    }

    public static StoneDefinition create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return new StoneDefinition(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static StoneDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return new StoneDefinition(register, name, properties, creativeTab);
    }

    private StoneBlockSet() {
        // Prevent instantiation
    }
}
