package com.github.syren_dev_tech.scylla.common.blocks.redstone.types;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ButtonBase extends ButtonBlock { // NOSONAR - Ignore number of parents

    public ButtonBase() {
        this(Properties.ofFullCopy(Blocks.STONE_BUTTON));
    }

    public ButtonBase(Properties properties) {
        super(BlockSetType.STONE, 0, properties);
    }

    public ButtonBase(Properties properties, BlockSetType blockSetType) {
        super(blockSetType, 0, properties);
    }

    public ButtonBase(Properties properties, BlockSetType blockSetType, int tickTime) {
        super(blockSetType, tickTime, properties);
    }
}
