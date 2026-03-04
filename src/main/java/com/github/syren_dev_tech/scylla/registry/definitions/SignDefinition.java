package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.world.level.block.Block;

public class SignDefinition<X extends Block, Y extends Block> {

    public final String name;
    public final BlockDefinition<X> defaultSign;
    public final BlockDefinition<Y> wall;

    public SignDefinition(String name, BlockDefinition<X> defaultSign, BlockDefinition<Y> wall) {
        this.name = name;
        this.defaultSign = defaultSign;
        this.wall = wall;
    }
}
