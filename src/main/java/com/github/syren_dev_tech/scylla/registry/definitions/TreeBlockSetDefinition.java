package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.plants.leaves.types.CustomLeaves;
import com.github.syren_dev_tech.scylla.crops.TreeFeatureBuilder;

public class TreeBlockSetDefinition {

    public final String name;
    public final WoodDefinition wood;
    public final BlockDefinition<CustomLeaves> leaves;
    public final TreeFeatureBuilder treeFeatureBuilder;

    public TreeBlockSetDefinition(String name, WoodDefinition wood, BlockDefinition<CustomLeaves> leaves, TreeFeatureBuilder treeFeatureBuilder) {
        this.name = name;
        this.wood = wood;
        this.leaves = leaves;
        this.treeFeatureBuilder = treeFeatureBuilder;
    }
}
