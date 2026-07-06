package com.github.syren_dev_tech.scylla.blocks.decoration;

import java.util.ArrayList;
import java.util.List;
import org.jspecify.annotations.Nullable;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FlowerPots {
    public static class PotWithFlower {

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, BlockDefinition<Block> flower) {
            return create(register, name, Properties.ofFullCopy(Blocks.FLOWER_POT), flower);
        }

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, BlockDefinition<Block> flower, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.FLOWER_POT), flower, creativeTab);
        }

        // Can't use "create" methods here because null is allowed for creative tabs.

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> flower) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(null, flower.registry, properties)));
        }

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> flower, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(null, flower.registry, properties)), creativeTab);
        }

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> flower, @Nullable BlockDefinition<FlowerPotBlock> emptyPot) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(emptyPot != null ? emptyPot.registry : null, flower.registry, properties)));
        }

        public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> flower, @Nullable BlockDefinition<FlowerPotBlock> emptyPot, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(emptyPot != null ? emptyPot.registry : null, flower.registry, properties)), creativeTab);
        }

        public static List<BlockDefinition<FlowerPotBlock>> create(ModRegister register, String name, List<BlockDefinition<Block>> flowers) {
            List<BlockDefinition<FlowerPotBlock>> pots = new ArrayList<>();

            flowers.forEach((BlockDefinition<Block> flower) -> pots.add(create(register, name, Properties.ofFullCopy(Blocks.FLOWER_POT), flower)));

            return pots;
        }

        public static List<BlockDefinition<FlowerPotBlock>> create(ModRegister register, String name, List<BlockDefinition<Block>> flowers, Properties properties) {
            List<BlockDefinition<FlowerPotBlock>> pots = new ArrayList<>();

            flowers.forEach((BlockDefinition<Block> flower) -> pots.add(create(register, name, properties, flower)));

            return pots;
        }

        private PotWithFlower() {
            // Prevent instantiation
        }
    }

    public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.FLOWER_POT));
    }

    public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FLOWER_POT), creativeTab);
    }

    public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(null, () -> Blocks.AIR, properties)));
    }

    public static BlockDefinition<FlowerPotBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FlowerPotBlock(null, () -> Blocks.AIR, properties)), creativeTab);
    }

    private FlowerPots() {
        // Prevent instantiation
    }
}
