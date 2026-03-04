package com.github.syren_dev_tech.scylla.registry.definitions;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.AttachedStemBlock;

public class StemDefinition {
    public final ResourceKey<Block> stemResourceKey;
    public final ResourceKey<Block> attachedStemResourceKey;
    public final BlockDefinition<StemBlock> stem;
    public final BlockDefinition<AttachedStemBlock> attachedStem;

    public StemDefinition(BlockDefinition<StemBlock> stem, BlockDefinition<AttachedStemBlock> attachedStem, ResourceKey<Block> stemResourceKey, ResourceKey<Block> attachedStemResourceKey) {
        this.stem = stem;
        this.attachedStem = attachedStem;
        this.stemResourceKey = stemResourceKey;
        this.attachedStemResourceKey = attachedStemResourceKey;
    }
}
