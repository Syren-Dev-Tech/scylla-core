package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DaylightDetectors {

    public static BlockDefinition<DaylightDetectorBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.DAYLIGHT_DETECTOR));
    }

    public static BlockDefinition<DaylightDetectorBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DAYLIGHT_DETECTOR), creativeTab);
    }

    public static BlockDefinition<DaylightDetectorBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DaylightDetectorBlock(properties)));
    }

    public static BlockDefinition<DaylightDetectorBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DaylightDetectorBlock(properties)), creativeTab);
    }

    private DaylightDetectors() {
        // Prevent instantiation
    }
}
