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

    public static final WoodDefinition create(ModRegister register, String name, Block sourceLog, Block sourcePlank, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, Properties.ofFullCopy(sourceLog), Properties.ofFullCopy(sourcePlank), creativeTab);
    }

    public static final WoodDefinition create(ModRegister register, String name, Properties logProperties, Properties plankProperties, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, logProperties, plankProperties, creativeTab);
    }

    private WoodBlockSet() {
        // Prevent instantiation
    }
}
