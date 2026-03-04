package com.github.syren_dev_tech.scylla.common.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EndRods {

    public static BlockDefinition<EndRodBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.END_ROD));
    }

    public static BlockDefinition<EndRodBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.END_ROD), creativeTab);
    }

    public static BlockDefinition<EndRodBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EndRodBlock(properties)));
    }

    public static BlockDefinition<EndRodBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new EndRodBlock(properties)), creativeTab);
    }

    private EndRods() {
        // Prevent instantiation
    }
}
