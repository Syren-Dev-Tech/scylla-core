package com.github.syren_dev_tech.scylla.common.blocks.plants.gourds;

import com.github.syren_dev_tech.scylla.common.blocks.plants.gourds.types.Gourd;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Gourds {

    public static final GourdDefinition create(ModRegister register, String name, Properties properties) {
        var gourd = register.blockRegistry.register(name, () -> new Gourd(properties));

        return new GourdDefinition(register, name, gourd);
    }

    public static final GourdDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var gourd = register.blockRegistry.register(name, () -> new Gourd(properties), creativeTab);

        return new GourdDefinition(register, name, gourd);
    }

    private Gourds() {
        // Prevent instantiation
    }
}
