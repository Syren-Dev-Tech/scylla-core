package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class Fungus {

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, BlockDefinition<Block> requiredBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.WARPED_FUNGUS), TreeFeatures.WARPED_FUNGUS_PLANTED, requiredBlock);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, BlockDefinition<Block> requiredBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WARPED_FUNGUS), TreeFeatures.WARPED_FUNGUS_PLANTED, requiredBlock, creativeTab);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> requiredBlock) {
        return create(register, name, properties, TreeFeatures.WARPED_FUNGUS_PLANTED, requiredBlock);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> requiredBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, TreeFeatures.WARPED_FUNGUS_PLANTED, requiredBlock, creativeTab);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, ResourceKey<ConfiguredFeature<?, ?>> feature, BlockDefinition<Block> requiredBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.WARPED_FUNGUS), feature, requiredBlock);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, ResourceKey<ConfiguredFeature<?, ?>> feature, BlockDefinition<Block> requiredBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WARPED_FUNGUS), feature, requiredBlock, creativeTab);
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, Properties properties, ResourceKey<ConfiguredFeature<?, ?>> feature, BlockDefinition<Block> requiredBlock) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FungusBlock(feature, requiredBlock.registry.get(), properties)));
    }

    public static final BlockDefinition<FungusBlock> create(ModRegister register, String name, Properties properties, ResourceKey<ConfiguredFeature<?, ?>> feature, BlockDefinition<Block> requiredBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FungusBlock(feature, requiredBlock.registry.get(), properties)), creativeTab);
    }

    private Fungus() {
        // Prevent instantiation
    }
}
