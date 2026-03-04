package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Lily {

    public static final BlockDefinition<WaterlilyBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.LILY_PAD));
    }

    public static final BlockDefinition<WaterlilyBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.LILY_PAD), creativeTab);
    }

    public static final BlockDefinition<WaterlilyBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WaterlilyBlock(properties)));
    }

    public static final BlockDefinition<WaterlilyBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new WaterlilyBlock(properties)), creativeTab);
    }

    private Lily() {
        // Prevent instantiation
    }
}
