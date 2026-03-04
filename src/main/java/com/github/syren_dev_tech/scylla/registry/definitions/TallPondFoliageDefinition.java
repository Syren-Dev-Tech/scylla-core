package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.BigDripleafStemBlock;

public class TallPondFoliageDefinition {

    public final String name;
    public final BlockDefinition<BigDripleafBlock> leaves;
    public final BlockDefinition<BigDripleafStemBlock> stem;

    public TallPondFoliageDefinition(String name, BlockDefinition<BigDripleafBlock> leaves, BlockDefinition<BigDripleafStemBlock> stem) {
        this.name = name;
        this.leaves = leaves;
        this.stem = stem;
    }
}
