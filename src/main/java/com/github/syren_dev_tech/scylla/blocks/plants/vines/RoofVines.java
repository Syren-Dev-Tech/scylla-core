package com.github.syren_dev_tech.scylla.blocks.plants.vines;

import com.github.syren_dev_tech.scylla.blocks.plants.vines.types.RoofVineBodyBlock;
import com.github.syren_dev_tech.scylla.blocks.plants.vines.types.RoofVineHeadBlock;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.TowerPlantDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RoofVines {
    public static class RoofVineBodies {

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES));
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES), creativeTab);
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES));
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES), creativeTab);
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, BlockDefinition<RoofVineHeadBlock> headBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), headBlock);
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, BlockDefinition<RoofVineHeadBlock> headBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), headBlock, creativeTab);
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<RoofVineHeadBlock> headBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new RoofVineBodyBlock(properties, headBlock)));
        }

        public static BlockDefinition<RoofVineBodyBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<RoofVineHeadBlock> headBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new RoofVineBodyBlock(properties, headBlock)), creativeTab);
        }

        private RoofVineBodies() {
            // Prevent instantiation
        }
    }

    public static class RoofVineTips {
        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT));
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT), creativeTab);
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES_PLANT));
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, BlockDefinition.of(() -> (RoofVineHeadBlock) Blocks.TWISTING_VINES_PLANT), creativeTab);
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, BlockDefinition<Block> bodyBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), bodyBlock);
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, BlockDefinition<Block> bodyBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), bodyBlock, creativeTab);
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> bodyBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new RoofVineHeadBlock(properties, bodyBlock)));
        }

        public static BlockDefinition<RoofVineHeadBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> bodyBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new RoofVineHeadBlock(properties, bodyBlock)), creativeTab);
        }

        private RoofVineTips() {
            // Prevent instantiation
        }
    }

    public static final TowerPlantDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT));
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), creativeTab);
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, Properties topProperties, Properties bodyProperties) {
        var top = RoofVineTips.create(register, name, topProperties);
        var body = RoofVineBodies.create(register, name + "_plant", bodyProperties, top);
        top.registry.get().setBodyBlock(body.registry.get());

        return new TowerPlantDefinition(name, body, top);
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, Properties topProperties, Properties bodyProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var top = RoofVineTips.create(register, name, topProperties, creativeTab);
        var body = RoofVineBodies.create(register, name + "_plant", bodyProperties, top, creativeTab);
        top.registry.get().setBodyBlock(body.registry.get());

        return new TowerPlantDefinition(name, body, top);
    }

    private RoofVines() {
        // Prevent instantiation
    }
}
