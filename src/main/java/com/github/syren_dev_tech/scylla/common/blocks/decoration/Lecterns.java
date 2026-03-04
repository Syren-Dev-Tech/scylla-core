package com.github.syren_dev_tech.scylla.common.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Lecterns {

    public static final BlockDefinition<LecternBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.LADDER));
    }

    public static final BlockDefinition<LecternBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.LADDER), creativeTab);
    }

    public static final BlockDefinition<LecternBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new LecternBlock(properties)));
    }

    public static final BlockDefinition<LecternBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new LecternBlock(properties)), creativeTab);
    }

    private Lecterns() {
        // Prevent instantiation
    }
}
