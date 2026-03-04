package com.github.syren_dev_tech.scylla.common.blocks.decoration.crystals;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.CrystalDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class CrystalSet {

    public static final CrystalDefinition create(ModRegister register, String name) {
        return new CrystalDefinition(register, name);
    }

    public static final CrystalDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new CrystalDefinition(register, name, creativeTab);
    }

    private CrystalSet() {
        // Prevent instantiation
    }
}
