package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;

public class CandleDefinition {

    public final String name;
    public final BlockDefinition<CandleBlock> standing;
    public final BlockDefinition<CandleCakeBlock> cake;

    public CandleDefinition(String name, BlockDefinition<CandleBlock> standing, BlockDefinition<CandleCakeBlock> cake) {
        this.name = name;
        this.standing = standing;
        this.cake = cake;
    }
}
