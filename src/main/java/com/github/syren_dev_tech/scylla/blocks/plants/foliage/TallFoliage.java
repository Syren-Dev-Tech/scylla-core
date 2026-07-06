package com.github.syren_dev_tech.scylla.blocks.plants.foliage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TallFoliage {

    public static BlockDefinition<TallGrassBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.TALL_GRASS));
    }

    public static BlockDefinition<TallGrassBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.TALL_GRASS), creativeTab);
    }

    public static BlockDefinition<TallGrassBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TallGrassBlock(properties)));
    }

    public static BlockDefinition<TallGrassBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TallGrassBlock(properties)), creativeTab);
    }

    private TallFoliage() {
        // Prevent instantiation
    }
}
