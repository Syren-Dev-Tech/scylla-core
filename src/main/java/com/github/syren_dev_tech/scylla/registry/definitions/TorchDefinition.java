package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;

public class TorchDefinition {

    public final String name;
    public final BlockDefinition<TorchBlock> standing;
    public final BlockDefinition<WallTorchBlock> wall;

    public TorchDefinition(String name, BlockDefinition<TorchBlock> standing, BlockDefinition<WallTorchBlock> wall) {
        this.name = name;
        this.standing = standing;
        this.wall = wall;
    }
}
