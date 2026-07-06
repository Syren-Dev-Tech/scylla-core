package com.github.syren_dev_tech.scylla.blocks.rails;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ActivatorRails {

    public static BlockDefinition<PoweredRailBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL));
    }

    public static BlockDefinition<PoweredRailBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL), creativeTab);
    }

    public static BlockDefinition<PoweredRailBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PoweredRailBlock(properties)));
    }

    public static BlockDefinition<PoweredRailBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PoweredRailBlock(properties)), creativeTab);
    }

    private ActivatorRails() {
        // Prevent instantiation
    }
}
