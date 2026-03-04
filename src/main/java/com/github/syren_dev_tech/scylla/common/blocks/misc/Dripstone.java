package com.github.syren_dev_tech.scylla.common.blocks.misc;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Dripstone {

    public static final BlockDefinition<PointedDripstoneBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE));
    }

    public static final BlockDefinition<PointedDripstoneBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE), creativeTab);
    }

    public static final BlockDefinition<PointedDripstoneBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PointedDripstoneBlock(properties)));
    }

    public static final BlockDefinition<PointedDripstoneBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new PointedDripstoneBlock(properties)), creativeTab);
    }

    private Dripstone() {
        // Prevent instantiation
    }
}
