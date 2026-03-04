package com.github.syren_dev_tech.scylla.common.blocks.rails;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DetectorRails {

    public static final BlockDefinition<DetectorRailBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.DETECTOR_RAIL));
    }

    public static final BlockDefinition<DetectorRailBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DETECTOR_RAIL), creativeTab);
    }

    public static final BlockDefinition<DetectorRailBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DetectorRailBlock(properties)));
    }

    public static final BlockDefinition<DetectorRailBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DetectorRailBlock(properties)), creativeTab);
    }

    private DetectorRails() {
        // Prevent instantiation
    }
}
