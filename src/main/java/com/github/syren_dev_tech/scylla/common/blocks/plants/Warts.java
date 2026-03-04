package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Warts {

    public static final BlockDefinition<NetherWartBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.NETHER_WART));
    }

    public static final BlockDefinition<NetherWartBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.NETHER_WART), creativeTab);
    }

    public static final BlockDefinition<NetherWartBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new NetherWartBlock(properties)));
    }

    public static final BlockDefinition<NetherWartBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new NetherWartBlock(properties)), creativeTab);
    }

    private Warts() {
        // Prevent instantiation
    }
}
