package com.github.syren_dev_tech.scylla.common.ores.types;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;

public class Ore extends DropExperienceBlock {

    public Ore(IntProvider xpRange, Properties properties) {
        super(xpRange, properties);
    }
}
