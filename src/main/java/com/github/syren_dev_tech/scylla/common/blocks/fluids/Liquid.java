package com.github.syren_dev_tech.scylla.common.blocks.fluids;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;

// Need some way to define liquid states for flowing fluids.

public class Liquid {

    public static final BlockDefinition<LiquidBlock> create(ModRegister register, String name, FlowingFluid flowingFluid) {
        return create(register, name, Properties.ofFullCopy(Blocks.WATER), flowingFluid);
    }

    public static final BlockDefinition<LiquidBlock> create(ModRegister register, String name, Properties properties, FlowingFluid flowingFluid) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new LiquidBlock(flowingFluid, properties)));
    }

    private Liquid() {
        // Prevent instantiation
    }
}
