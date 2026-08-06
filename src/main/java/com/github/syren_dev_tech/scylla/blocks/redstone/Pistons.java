package com.github.syren_dev_tech.scylla.blocks.redstone;

import com.github.syren_dev_tech.scylla.collections.Triplet;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Pistons {

    public static class PistonBases {

        public static BlockDefinition<PistonBaseBlock> create(ModRegister register, String name, boolean sticky) {
            return create(register, name, Properties.ofFullCopy(Blocks.PISTON), sticky);
        }

        public static BlockDefinition<PistonBaseBlock> create(ModRegister register, String name, boolean sticky, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.PISTON), sticky, creativeTab);
        }

        public static BlockDefinition<PistonBaseBlock> create(ModRegister register, String name, Properties properties, boolean sticky) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new PistonBaseBlock(sticky, properties)));
        }

        public static BlockDefinition<PistonBaseBlock> create(ModRegister register, String name, Properties properties, boolean sticky, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new PistonBaseBlock(sticky, properties)), creativeTab);
        }

        private PistonBases() {
            // Prevent instantiation
        }
    }

    public static class PistonHeads {

        public static BlockDefinition<PistonHeadBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.PISTON_HEAD));
        }

        public static BlockDefinition<PistonHeadBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.PISTON_HEAD), creativeTab);
        }

        public static BlockDefinition<PistonHeadBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new PistonHeadBlock(properties)));
        }

        public static BlockDefinition<PistonHeadBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new PistonHeadBlock(properties)), creativeTab);
        }

        private PistonHeads() {
            // Prevent instantiation
        }
    }

    public static class MovingPistons {

        public static BlockDefinition<MovingPistonBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.MOVING_PISTON));
        }

        public static BlockDefinition<MovingPistonBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.MOVING_PISTON), creativeTab);
        }

        public static BlockDefinition<MovingPistonBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new MovingPistonBlock(properties)));
        }

        public static BlockDefinition<MovingPistonBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new MovingPistonBlock(properties)), creativeTab);
        }

        private MovingPistons() {
            // Prevent instantiation
        }
    }

    public static final Triplet<BlockDefinition<PistonBaseBlock>, BlockDefinition<PistonHeadBlock>, BlockDefinition<MovingPistonBlock>> createSticky(ModRegister register, String name) {
        return create(register, name, false);
    }

    public static final Triplet<BlockDefinition<PistonBaseBlock>, BlockDefinition<PistonHeadBlock>, BlockDefinition<MovingPistonBlock>> createSticky(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, false, creativeTab);
    }

    public static final Triplet<BlockDefinition<PistonBaseBlock>, BlockDefinition<PistonHeadBlock>, BlockDefinition<MovingPistonBlock>> create(ModRegister register, String name, boolean sticky) {
        var pistonBaseBlock = PistonBases.create(register, name, sticky);
        var pistonHeadBlock = PistonHeads.create(register, name + "_head");
        var movingPistonBlock = MovingPistons.create(register, "moving_" + name);

        return new Triplet<>(pistonBaseBlock, pistonHeadBlock, movingPistonBlock);
    }

    public static final Triplet<BlockDefinition<PistonBaseBlock>, BlockDefinition<PistonHeadBlock>, BlockDefinition<MovingPistonBlock>> create(ModRegister register, String name, boolean sticky, ResourceKey<CreativeModeTab> creativeTab) {
        var pistonBaseBlock = PistonBases.create(register, name, sticky, creativeTab);
        var pistonHeadBlock = PistonHeads.create(register, name + "_head", creativeTab);
        var movingPistonBlock = MovingPistons.create(register, "moving_" + name, creativeTab);

        return new Triplet<>(pistonBaseBlock, pistonHeadBlock, movingPistonBlock);
    }

    private Pistons() {
        // Prevent instantiation
    }
}
