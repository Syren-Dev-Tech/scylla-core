package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SeaPickles {

    public static final BlockDefinition<SeaPickleBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.SEA_PICKLE));
    }

    public static final BlockDefinition<SeaPickleBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.SEA_PICKLE), creativeTab);
    }

    public static final BlockDefinition<SeaPickleBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SeaPickleBlock(properties)));
    }

    public static final BlockDefinition<SeaPickleBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SeaPickleBlock(properties)), creativeTab);
    }

    private SeaPickles() {
        // Prevent instantiation
    }
}
