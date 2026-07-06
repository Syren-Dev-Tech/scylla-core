package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Dispensers {

    public static BlockDefinition<DispenserBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.DISPENSER));
    }

    public static BlockDefinition<DispenserBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DISPENSER), creativeTab);
    }

    public static BlockDefinition<DispenserBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DispenserBlock(properties)));
    }

    public static BlockDefinition<DispenserBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new DispenserBlock(properties)), creativeTab);
    }

    private Dispensers() {
        // Prevent instantiation
    }
}
