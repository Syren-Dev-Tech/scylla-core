package com.github.syren_dev_tech.scylla.common.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Beds {

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name) {
        return create(register, name, DyeColor.WHITE, Properties.ofFullCopy(Blocks.WHITE_BED));
    }

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, DyeColor.WHITE, Properties.ofFullCopy(Blocks.WHITE_BED), creativeTab);
    }

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name, DyeColor color) {
        return create(register, name, color, Properties.ofFullCopy(Blocks.WHITE_BED));
    }

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, color, Properties.ofFullCopy(Blocks.WHITE_BED), creativeTab);
    }

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name, DyeColor color, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BedBlock(color, properties)));
    }

    public static final BlockDefinition<BedBlock> create(ModRegister register, String name, DyeColor color, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BedBlock(color, properties)), creativeTab);
    }

    private Beds() {
        // Prevent instantiation
    }
}
