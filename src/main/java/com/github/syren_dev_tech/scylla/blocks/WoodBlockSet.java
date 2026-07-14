package com.github.syren_dev_tech.scylla.blocks;

import com.github.syren_dev_tech.scylla.blocks.plants.leaves.Leaves;
import com.github.syren_dev_tech.scylla.crops.TreeFeatureBuilder;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.TreeBlockSetDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.WoodDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.WoodDefinition.LogDefinition.LogDefinitionProperties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WoodBlockSet {

    public static final WoodDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, creativeTab);
    }

    public static final WoodDefinition create(ModRegister register, String name, Block sourceLog, Block sourcePlank, ResourceKey<CreativeModeTab> creativeTab) {
        var logProperties = new LogDefinitionProperties();
        logProperties.logProperties = Properties.ofFullCopy(sourceLog);
        logProperties.strippedLogProperties = Properties.ofFullCopy(sourceLog);
        logProperties.woodProperties = Properties.ofFullCopy(sourceLog);
        logProperties.strippedWoodProperties = Properties.ofFullCopy(sourceLog);

        return new WoodDefinition(register, name, logProperties, Properties.ofFullCopy(sourcePlank), creativeTab);
    }

    public static final WoodDefinition create(ModRegister register, String name, LogDefinitionProperties logProperties, Properties plankProperties, ResourceKey<CreativeModeTab> creativeTab) {
        return new WoodDefinition(register, name, logProperties, plankProperties, creativeTab);
    }

    public static final TreeBlockSetDefinition createTree(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return createTree(register, name, Blocks.OAK_LOG, Blocks.OAK_PLANKS, Blocks.OAK_LEAVES, TreeFeatureBuilder.create(), creativeTab);
    }

    public static final TreeBlockSetDefinition createTree(ModRegister register, String name, Block sourceLog, Block sourcePlank, Block sourceLeaves, ResourceKey<CreativeModeTab> creativeTab) {
        return createTree(register, name, sourceLog, sourcePlank, sourceLeaves, TreeFeatureBuilder.create(), creativeTab);
    }

    public static final TreeBlockSetDefinition createTree(ModRegister register, String name, Block sourceLog, Block sourcePlank, Block sourceLeaves, TreeFeatureBuilder featureBuilder, ResourceKey<CreativeModeTab> creativeTab) {
        var woodDefinition = create(register, name, sourceLog, sourcePlank, creativeTab);
        var leavesDefinition = Leaves.create(register, name + "_leaves", Properties.ofFullCopy(sourceLeaves), creativeTab);
        var builder = (featureBuilder == null ? TreeFeatureBuilder.create() : featureBuilder).withWood(woodDefinition.logs.log).withLeaves(leavesDefinition);

        return new TreeBlockSetDefinition(name, woodDefinition, leavesDefinition, builder);
    }

    public static final TreeBlockSetDefinition createTree(ModRegister register, String name, LogDefinitionProperties logProperties, Properties plankProperties, Properties leavesProperties, ResourceKey<CreativeModeTab> creativeTab) {
        return createTree(register, name, logProperties, plankProperties, leavesProperties, TreeFeatureBuilder.create(), creativeTab);
    }

    public static final TreeBlockSetDefinition createTree(ModRegister register, String name, LogDefinitionProperties logProperties, Properties plankProperties, Properties leavesProperties, TreeFeatureBuilder featureBuilder, ResourceKey<CreativeModeTab> creativeTab) {
        var woodDefinition = create(register, name, logProperties, plankProperties, creativeTab);
        var leavesDefinition = Leaves.create(register, name + "_leaves", leavesProperties, creativeTab);
        var builder = (featureBuilder == null ? TreeFeatureBuilder.create() : featureBuilder).withWood(woodDefinition.logs.log).withLeaves(leavesDefinition);

        return new TreeBlockSetDefinition(name, woodDefinition, leavesDefinition, builder);
    }

    private WoodBlockSet() {
        // Prevent instantiation
    }
}
