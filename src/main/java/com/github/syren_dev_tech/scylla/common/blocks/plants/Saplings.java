package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Saplings {

    public static final BlockDefinition<SaplingBlock> create(ModRegister register, String name, TreeGrower treeGrower) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_SAPLING), treeGrower);
    }

    public static final BlockDefinition<SaplingBlock> create(ModRegister register, String name, TreeGrower treeGrower, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.OAK_SAPLING), treeGrower, creativeTab);
    }

    public static final BlockDefinition<SaplingBlock> create(ModRegister register, String name, Properties properties, TreeGrower treeGrower) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SaplingBlock(treeGrower, properties)));
    }

    public static final BlockDefinition<SaplingBlock> create(ModRegister register, String name, Properties properties, TreeGrower treeGrower, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SaplingBlock(treeGrower, properties)), creativeTab);
    }

    private Saplings() {
        // Prevent instantiation
    }
}
