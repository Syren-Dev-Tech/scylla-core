package com.github.syren_dev_tech.scylla.blocks.plants.leaves;

import com.github.syren_dev_tech.scylla.blocks.plants.leaves.types.CustomLeaves;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Leaves {

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_LEAVES));
    }

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_LEAVES), creativeTab);
    }

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomLeaves(properties)));
    }

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomLeaves(properties)), creativeTab);
    }

    // Adding particles makes the leaves like cherry blossom leaves instead of
    // defaults.

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name, Properties properties, ParticleOptions particles) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomLeaves(properties, particles)));
    }

    public static BlockDefinition<CustomLeaves> create(ModRegister register, String name, Properties properties, ParticleOptions particles, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomLeaves(properties, particles)), creativeTab);
    }

    private Leaves() {
        // Prevent instantiation
    }
}
