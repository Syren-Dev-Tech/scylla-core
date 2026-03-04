package com.github.syren_dev_tech.scylla.common.blocks.plants.vines;

import com.github.syren_dev_tech.scylla.common.blocks.plants.vines.types.FloorVineBodyBlock;
import com.github.syren_dev_tech.scylla.common.blocks.plants.vines.types.FloorVineHeadBlock;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.TowerPlantDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FloorVines {
    public static class FloorVineBodies {

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), BlockDefinition.of(() -> (FloorVineHeadBlock) Blocks.TWISTING_VINES));
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), BlockDefinition.of(() -> (FloorVineHeadBlock) Blocks.TWISTING_VINES), creativeTab);
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, BlockDefinition.of(() -> (FloorVineHeadBlock) Blocks.TWISTING_VINES));
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, BlockDefinition.of(() -> (FloorVineHeadBlock) Blocks.TWISTING_VINES), creativeTab);
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, BlockDefinition<FloorVineHeadBlock> headBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), headBlock);
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, BlockDefinition<FloorVineHeadBlock> headBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT), headBlock, creativeTab);
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<FloorVineHeadBlock> headBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FloorVineBodyBlock(properties, headBlock.registry.get())));
        }

        public static final BlockDefinition<FloorVineBodyBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<FloorVineHeadBlock> headBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FloorVineBodyBlock(properties, headBlock.registry.get())), creativeTab);
        }

        private FloorVineBodies() {
            // Prevent instantiation
        }
    }

    public static class FloorVineTips {

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT));
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT), creativeTab);
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT));
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, BlockDefinition.of(() -> Blocks.TWISTING_VINES_PLANT), creativeTab);
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, BlockDefinition<Block> bodyBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), bodyBlock);
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, BlockDefinition<Block> bodyBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TWISTING_VINES), bodyBlock, creativeTab);
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> bodyBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FloorVineHeadBlock(properties, bodyBlock.registry.get())));
        }

        public static final BlockDefinition<FloorVineHeadBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> bodyBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new FloorVineHeadBlock(properties, bodyBlock.registry.get())), creativeTab);
        }

        private FloorVineTips() {
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
        var top = FloorVineTips.create(register, name, topProperties);
        var body = FloorVineBodies.create(register, name + "_plant", bodyProperties, top);
        top.registry.get().setBodyBlock(body.registry.get());

        return new TowerPlantDefinition(name, body, top);
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, Properties topProperties, Properties bodyProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var top = FloorVineTips.create(register, name, topProperties, creativeTab);
        var body = FloorVineBodies.create(register, name + "_plant", bodyProperties, top, creativeTab);
        top.registry.get().setBodyBlock(body.registry.get());

        return new TowerPlantDefinition(name, body, top);
    }

    private FloorVines() {
        // Prevent instantiation
    }
}
