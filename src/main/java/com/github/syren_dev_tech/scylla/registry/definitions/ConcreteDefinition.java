package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;

public class ConcreteDefinition {

    public final String name;
    public final BlockDefinition<ConcretePowderBlock> powder;
    public final BlockDefinition<Block> hardened;

    public ConcreteDefinition(String name, BlockDefinition<ConcretePowderBlock> powder, BlockDefinition<Block> hardened) {
        this.name = name;
        this.powder = powder;
        this.hardened = hardened;
    }
}
