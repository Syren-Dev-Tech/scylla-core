package com.github.syren_dev_tech.scylla.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Beam

public class Beacons {

    public static BlockDefinition<BeaconBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.BEACON));
    }

    public static BlockDefinition<BeaconBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.BEACON), creativeTab);
    }

    public static BlockDefinition<BeaconBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BeaconBlock(properties)));
    }

    public static BlockDefinition<BeaconBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BeaconBlock(properties)), creativeTab);
    }

    private Beacons() {
        // Prevent instantiation
    }
}
