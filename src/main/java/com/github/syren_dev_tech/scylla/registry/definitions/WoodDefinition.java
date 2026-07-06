package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.FenceBlocks;
import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.blocks.PillarBlocks;
import com.github.syren_dev_tech.scylla.blocks.Slabs;
import com.github.syren_dev_tech.scylla.blocks.Stairs;
import com.github.syren_dev_tech.scylla.blocks.decoration.signs.HangingSigns;
import com.github.syren_dev_tech.scylla.blocks.decoration.signs.Signs;
import com.github.syren_dev_tech.scylla.blocks.redstone.Buttons;
import com.github.syren_dev_tech.scylla.blocks.redstone.Doors;
import com.github.syren_dev_tech.scylla.blocks.redstone.FenceGates;
import com.github.syren_dev_tech.scylla.blocks.redstone.PressurePlates;
import com.github.syren_dev_tech.scylla.blocks.redstone.TrapDoors;
import com.github.syren_dev_tech.scylla.blocks.redstone.types.ButtonBase;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WoodDefinition {

    public final String name;
    public final LogDefinition logs;
    public final PlanksDefinition planks;
    public final BoatDefinition boats;

    public WoodDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        this.logs = new LogDefinition(register, name, creativeTab);
        this.planks = new PlanksDefinition(register, name, creativeTab);
        this.boats = new BoatDefinition(register, name, creativeTab);
    }

    public WoodDefinition(ModRegister register, String name, Properties woodProperties, Properties plankProperties, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        this.logs = new LogDefinition(register, name, woodProperties, creativeTab);
        this.planks = new PlanksDefinition(register, name, plankProperties, creativeTab);
        this.boats = new BoatDefinition(register, name, creativeTab);
    }

    public class LogDefinition {

        public final String name;
        public final BlockDefinition<RotatedPillarBlock> log;
        public final BlockDefinition<RotatedPillarBlock> strippedLog;
        public final BlockDefinition<Block> wood;
        public final BlockDefinition<Block> strippedWood;

        public LogDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            this.name = name;

            this.log = PillarBlocks.create(register, name + "_log", creativeTab);
            this.strippedLog = PillarBlocks.create(register, "stripped_" + name + "_log", creativeTab);
            this.wood = ModBlocks.create(register, name + "_wood", creativeTab);
            this.strippedWood = ModBlocks.create(register, "stripped_" + name + "_wood", creativeTab);
        }

        public LogDefinition(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            this.name = name;

            this.log = PillarBlocks.create(register, name + "_log", properties, creativeTab);
            this.strippedLog = PillarBlocks.create(register, "stripped_" + name + "_log", properties, creativeTab);
            this.wood = ModBlocks.create(register, name + "_wood", properties, creativeTab);
            this.strippedWood = ModBlocks.create(register, "stripped_" + name + "_wood", properties, creativeTab);
        }
    }

    public class PlanksDefinition {

        public final String name;
        public final BlockSetType blockSetType;
        public final WoodType woodType;

        public final BlockDefinition<Block> planks;
        public final BlockDefinition<StairBlock> stairs;
        public final BlockDefinition<SlabBlock> slab;
        public final BlockDefinition<FenceBlock> fence;
        public final BlockDefinition<FenceGateBlock> fenceGate;
        public final BlockDefinition<DoorBlock> door;
        public final BlockDefinition<TrapDoorBlock> trapdoor;
        public final BlockDefinition<PressurePlateBlock> pressurePlate;
        public final BlockDefinition<ButtonBase> button;
        public final SignDefinition<StandingSignBlock, WallSignBlock> sign;
        public final SignDefinition<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign;

        public PlanksDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            this.name = name;

            this.blockSetType = new BlockSetType(name);
            BlockSetType.register(blockSetType);

            this.woodType = new WoodType(name, blockSetType);
            WoodType.register(woodType);

            var properties = Properties.ofFullCopy(Blocks.OAK_PLANKS);
            this.planks = ModBlocks.create(register, name + "_planks", properties, creativeTab);
            this.stairs = Stairs.create(register, name + "_stairs", properties, planks, creativeTab);
            this.slab = Slabs.create(register, name + "_slab", properties, creativeTab);
            this.fence = FenceBlocks.create(register, name + "_fence", properties, creativeTab);
            this.fenceGate = FenceGates.create(register, name + "_fence_gate", properties, woodType, creativeTab);
            this.door = Doors.create(register, name + "_door", properties, blockSetType, creativeTab);
            this.trapdoor = TrapDoors.create(register, name + "_trapdoor", properties, blockSetType, creativeTab);

            this.pressurePlate = PressurePlates.create(register, name + "_pressure_plate", properties, blockSetType, creativeTab);
            this.button = Buttons.create(register, name + "_button", properties, creativeTab);

            this.sign = Signs.create(register, name, properties, creativeTab);
            this.hangingSign = HangingSigns.create(register, name, properties, creativeTab);
        }

        public PlanksDefinition(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            this.name = name;

            this.blockSetType = new BlockSetType(name);
            BlockSetType.register(blockSetType);

            this.woodType = new WoodType(name, blockSetType);
            WoodType.register(woodType);

            this.planks = ModBlocks.create(register, name + "_planks", properties, creativeTab);
            this.stairs = Stairs.create(register, name + "_stairs", properties, planks, creativeTab);
            this.slab = Slabs.create(register, name + "_slab", properties, creativeTab);
            this.fence = FenceBlocks.create(register, name + "_fence", properties, creativeTab);
            this.fenceGate = FenceGates.create(register, name + "_fence_gate", properties, woodType, creativeTab);
            this.door = Doors.create(register, name + "_door", properties, blockSetType, creativeTab);
            this.trapdoor = TrapDoors.create(register, name + "_trapdoor", properties, blockSetType, creativeTab);

            this.pressurePlate = PressurePlates.create(register, name + "_pressure_plate", properties, blockSetType, creativeTab);
            this.button = Buttons.create(register, name + "_button", properties, creativeTab);

            this.sign = Signs.create(register, name, properties, creativeTab);
            this.hangingSign = HangingSigns.create(register, name, properties, creativeTab);
        }
    }

    public class BoatDefinition {

        public final String name;
        public final ItemDefinition<BoatItem> boat;
        public final ItemDefinition<BoatItem> chestBoat;

        public BoatDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            this.name = name;

            this.boat = register.itemRegistry.register(name + "_boat", () -> new BoatItem(false, Boat.Type.OAK, new Item.Properties().stacksTo(1)), creativeTab);
            this.chestBoat = register.itemRegistry.register(name + "_chest_boat", () -> new BoatItem(true, Boat.Type.OAK, new Item.Properties().stacksTo(1)), creativeTab);
        }
    }
}
