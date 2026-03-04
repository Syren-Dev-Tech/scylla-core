package com.github.syren_dev_tech.scylla.registry.definitions;

public class BranchingPlantDefinition {

    public final String name;
    public final BlockDefinition<?> stalkBlock;
    public final BlockDefinition<?> flowerBlock;

    public BranchingPlantDefinition(String name, BlockDefinition<?> stalkBlock, BlockDefinition<?> flowerBlock) {
        this.name = name;
        this.stalkBlock = stalkBlock;
        this.flowerBlock = flowerBlock;
    }
}
