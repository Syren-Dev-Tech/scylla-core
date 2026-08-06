package com.github.syren_dev_tech.scylla.utilities;

import java.util.function.Consumer;

import net.minecraft.world.item.DyeColor;

public class Colors {
    public static final DyeColor[] COLORS = new DyeColor[] {DyeColor.WHITE, DyeColor.ORANGE, DyeColor.MAGENTA, DyeColor.LIGHT_BLUE, DyeColor.YELLOW, DyeColor.LIME, DyeColor.PINK, DyeColor.GRAY, DyeColor.LIGHT_GRAY, DyeColor.CYAN, DyeColor.PURPLE, DyeColor.BLUE, DyeColor.BROWN, DyeColor.GREEN, DyeColor.RED, DyeColor.BLACK};

    private Colors() {}

    public static final void apply(Consumer<DyeColor> consumer) {
        for (DyeColor dye : COLORS) {
            consumer.accept(dye);
        }
    }
}
