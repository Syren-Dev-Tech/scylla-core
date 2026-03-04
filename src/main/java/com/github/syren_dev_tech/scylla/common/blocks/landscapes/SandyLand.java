package com.github.syren_dev_tech.scylla.common.blocks.landscapes;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SandyLand {
    public static final ColorRGBA SAND_DUST_COLOR = new ColorRGBA(14406560);
    public static final ColorRGBA RED_SAND_DUST_COLOR = new ColorRGBA(11098145);
    public static final ColorRGBA GRAVEL_DUST_COLOR = new ColorRGBA(4210752);

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.SAND), SAND_DUST_COLOR);
    }

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.SAND), SAND_DUST_COLOR, creativeTab);
    }

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, SAND_DUST_COLOR);
    }

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, SAND_DUST_COLOR, creativeTab);
    }

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, Properties properties, ColorRGBA dustColor) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ColoredFallingBlock(dustColor, properties)));
    }

    public static final BlockDefinition<ColoredFallingBlock> create(ModRegister register, String name, Properties properties, ColorRGBA dustColor, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ColoredFallingBlock(dustColor, properties)), creativeTab);
    }

    private SandyLand() {
        // Prevent instantiation
    }
}
