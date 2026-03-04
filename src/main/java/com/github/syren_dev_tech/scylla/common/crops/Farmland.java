package com.github.syren_dev_tech.scylla.common.crops;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Farmland {

    private Farmland() {}

    public static final BlockDefinition<FarmBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.FARMLAND));
    }

    public static final BlockDefinition<FarmBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FARMLAND), creativeTab);
    }

    public static final BlockDefinition<FarmBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FarmBlock(properties)));
    }

    public static final BlockDefinition<FarmBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FarmBlock(properties)), creativeTab);
    }
}
