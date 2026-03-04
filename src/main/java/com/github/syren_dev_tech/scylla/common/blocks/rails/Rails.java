package com.github.syren_dev_tech.scylla.common.blocks.rails;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class Rails {

    public static final BlockDefinition<RailBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.RAIL));
    }

    public static final BlockDefinition<RailBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.RAIL), creativeTab);
    }

    public static final BlockDefinition<RailBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new RailBlock(properties)));
    }

    public static final BlockDefinition<RailBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new RailBlock(properties)), creativeTab);
    }

    private Rails() {
        // Prevent instantiation
    }
}
