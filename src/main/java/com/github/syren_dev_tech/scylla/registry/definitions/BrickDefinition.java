package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.blocks.Slabs;
import com.github.syren_dev_tech.scylla.blocks.Stairs;
import com.github.syren_dev_tech.scylla.blocks.WallBlocks;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class BrickDefinition {

    public final String name;
    public final BlockDefinition<Block> block;
    public final BlockDefinition<StairBlock> stairs;
    public final BlockDefinition<SlabBlock> slab;
    public final BlockDefinition<WallBlock> wall;

    public BrickDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        BlockSetType blockSetType = new BlockSetType(name);
        BlockSetType.register(blockSetType);

        var brickProperties = Properties.ofFullCopy(Blocks.BRICKS);
        this.block = ModBlocks.create(register, name, brickProperties, creativeTab);
        this.stairs = Stairs.create(register, name + "_stairs", brickProperties, block, creativeTab);
        this.slab = Slabs.create(register, name + "_slab", brickProperties, creativeTab);
        this.wall = WallBlocks.create(register, name + "_wall", brickProperties, creativeTab);
    }

    public BrickDefinition(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        BlockSetType blockSetType = new BlockSetType(name);
        BlockSetType.register(blockSetType);

        this.block = ModBlocks.create(register, name, properties, creativeTab);
        this.stairs = Stairs.create(register, name + "_stairs", properties, block, creativeTab);
        this.slab = Slabs.create(register, name + "_slab", properties, creativeTab);
        this.wall = WallBlocks.create(register, name + "_wall", properties, creativeTab);
    }
}
