package com.github.syren_dev_tech.scylla.common.blocks;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.WoodDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class WoodBlockSet {

    public static final WoodDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, creativeTab);
    }

    private WoodBlockSet() {
        // Prevent instantiation
    }
}
