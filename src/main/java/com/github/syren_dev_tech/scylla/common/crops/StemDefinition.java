package com.github.syren_dev_tech.scylla.common.crops;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.AttachedStemBlock;

public class StemDefinition {
    public final ResourceKey<Block> stemResourceKey;
    public final ResourceKey<Block> attachedStemResourceKey;
    public final Supplier<StemBlock> stem;
    public final Supplier<AttachedStemBlock> attachedStem;

    public StemDefinition(Supplier<StemBlock> stem, Supplier<AttachedStemBlock> attachedStem, ResourceKey<Block> stemResourceKey, ResourceKey<Block> attachedStemResourceKey) {
        this.stem = stem;
        this.attachedStem = attachedStem;
        this.stemResourceKey = stemResourceKey;
        this.attachedStemResourceKey = attachedStemResourceKey;
    }
}
