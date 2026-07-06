package com.github.syren_dev_tech.scylla.blocks.decoration.crafting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BrewingStands {

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.BREWING_STAND));
    }

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.BREWING_STAND), creativeTab);
    }

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name, Block sourceBlock) {
        return create(register, name, Properties.ofFullCopy(sourceBlock));
    }

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name, Block sourceBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(sourceBlock), creativeTab);
    }

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BrewingStandBlock(properties)));
    }

    public static BlockDefinition<BrewingStandBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BrewingStandBlock(properties)), creativeTab);
    }

    private BrewingStands() {
        // Prevent instantiation
    }
}
