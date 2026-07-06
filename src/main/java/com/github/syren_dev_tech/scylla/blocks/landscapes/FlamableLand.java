package com.github.syren_dev_tech.scylla.blocks.landscapes;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherrackBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FlamableLand {

    public static BlockDefinition<NetherrackBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.NETHERRACK));
    }

    public static BlockDefinition<NetherrackBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.NETHERRACK), creativeTab);
    }

    public static BlockDefinition<NetherrackBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new NetherrackBlock(properties)));
    }

    public static BlockDefinition<NetherrackBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new NetherrackBlock(properties)), creativeTab);
    }

    private FlamableLand() {
        // Prevent instantiation
    }
}
