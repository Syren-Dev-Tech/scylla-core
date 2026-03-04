package com.github.syren_dev_tech.scylla.common.crops.types;

import net.minecraft.world.level.block.Block;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.item.ItemNameBlockItem;

public class SeedBase extends ItemNameBlockItem {
    public SeedBase(BlockDefinition<? extends Block> cropBlock, Properties properties) {
        super(cropBlock.registry.get(), properties);
    }
}
