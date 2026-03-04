package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.common.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.common.blocks.Slabs;
import com.github.syren_dev_tech.scylla.common.blocks.Stairs;
import com.github.syren_dev_tech.scylla.common.blocks.WallBlocks;
import com.github.syren_dev_tech.scylla.common.blocks.redstone.Buttons;
import com.github.syren_dev_tech.scylla.common.blocks.redstone.PressurePlates;
import com.github.syren_dev_tech.scylla.common.blocks.redstone.types.ButtonBase;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class StoneDefinition {

    public final String name;
    public final BlockDefinition<Block> block;
    public final BlockDefinition<StairBlock> stairs;
    public final BlockDefinition<SlabBlock> slab;
    public final BlockDefinition<WallBlock> wall;
    public final BlockDefinition<PressurePlateBlock> pressurePlate;
    public final BlockDefinition<ButtonBase> button;

    public StoneDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        BlockSetType blockSetType = new BlockSetType(name);
        BlockSetType.register(blockSetType);

        var stoneProperties = Properties.ofFullCopy(Blocks.STONE);
        this.block = ModBlocks.create(register, name, stoneProperties, creativeTab);
        this.stairs = Stairs.create(register, name + "_stairs", stoneProperties, block, creativeTab);
        this.slab = Slabs.create(register, name + "_slab", stoneProperties, creativeTab);
        this.wall = WallBlocks.create(register, name + "_wall", stoneProperties, creativeTab);
        this.pressurePlate = PressurePlates.create(register, name + "_pressure_plate", stoneProperties, blockSetType, creativeTab);
        this.button = Buttons.create(register, name + "_button", stoneProperties, creativeTab);
    }

    public StoneDefinition(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        BlockSetType blockSetType = new BlockSetType(name);
        BlockSetType.register(blockSetType);

        this.block = ModBlocks.create(register, name, properties, creativeTab);
        this.stairs = Stairs.create(register, name + "_stairs", properties, block, creativeTab);
        this.slab = Slabs.create(register, name + "_slab", properties, creativeTab);
        this.wall = WallBlocks.create(register, name + "_wall", properties, creativeTab);
        this.pressurePlate = PressurePlates.create(register, name + "_pressure_plate", properties, blockSetType, creativeTab);
        this.button = Buttons.create(register, name + "_button", properties, creativeTab);
    }
}
