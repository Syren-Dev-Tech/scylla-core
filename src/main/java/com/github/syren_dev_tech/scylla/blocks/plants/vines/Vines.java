package com.github.syren_dev_tech.scylla.blocks.plants.vines;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Vines {

    public static BlockDefinition<VineBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.VINE));
    }

    public static BlockDefinition<VineBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.VINE), creativeTab);
    }

    public static BlockDefinition<VineBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new VineBlock(properties)));
    }

    public static BlockDefinition<VineBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new VineBlock(properties)), creativeTab);
    }

    private Vines() {
        // Prevent instantiation
    }
}
