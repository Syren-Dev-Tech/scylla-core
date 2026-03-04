package com.github.syren_dev_tech.scylla.common.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RedstoneComparators {

    public static final BlockDefinition<ComparatorBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.COMPARATOR));
    }

    public static final BlockDefinition<ComparatorBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.COMPARATOR), creativeTab);
    }

    public static final BlockDefinition<ComparatorBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ComparatorBlock(properties)));
    }

    public static final BlockDefinition<ComparatorBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ComparatorBlock(properties)), creativeTab);
    }

    private RedstoneComparators() {
        // Prevent instantiation
    }
}
