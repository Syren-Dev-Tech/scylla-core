package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.BranchingPlantDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ChorusPlants {

    public static class ChorusStalks {

        public static final BlockDefinition<ChorusPlantBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_PLANT));
        }

        public static final BlockDefinition<ChorusPlantBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_PLANT), creativeTab);
        }

        public static final BlockDefinition<ChorusPlantBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChorusPlantBlock(properties)));
        }

        public static final BlockDefinition<ChorusPlantBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChorusPlantBlock(properties)), creativeTab);
        }

        private ChorusStalks() {
            // Prevent instantiation
        }
    }

    public static class ChorusFlowers {

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_FLOWER));
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_FLOWER), creativeTab);
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, BlockDefinition<ChorusPlantBlock> plantBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_FLOWER), plantBlock);
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, BlockDefinition<ChorusPlantBlock> plantBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.CHORUS_FLOWER), plantBlock, creativeTab);
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, BlockDefinition.of(() -> (ChorusPlantBlock) Blocks.CHORUS_PLANT));
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, BlockDefinition.of(() -> (ChorusPlantBlock) Blocks.CHORUS_PLANT), creativeTab);
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<ChorusPlantBlock> plantBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChorusFlowerBlock(plantBlock.registry.get(), properties)));
        }

        public static final BlockDefinition<ChorusFlowerBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<ChorusPlantBlock> plantBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChorusFlowerBlock(plantBlock.registry.get(), properties)), creativeTab);
        }

        private ChorusFlowers() {
            // Prevent instantiation
        }
    }

    public static final BranchingPlantDefinition create(ModRegister register, String name) {
        var plantBlock = ChorusStalks.create(register, name);
        var flowerBlock = ChorusFlowers.create(register, name, plantBlock);

        return new BranchingPlantDefinition(name, plantBlock, flowerBlock);
    }

    public static final BranchingPlantDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        var plantBlock = ChorusStalks.create(register, name, creativeTab);
        var flowerBlock = ChorusFlowers.create(register, name, plantBlock, creativeTab);

        return new BranchingPlantDefinition(name, plantBlock, flowerBlock);
    }

    public static final BranchingPlantDefinition create(ModRegister register, String name, Properties stalkProperties, Properties flowerProperties) {
        var plantBlock = ChorusStalks.create(register, name, stalkProperties);
        var flowerBlock = ChorusFlowers.create(register, name, flowerProperties, plantBlock);

        return new BranchingPlantDefinition(name, plantBlock, flowerBlock);
    }

    public static final BranchingPlantDefinition create(ModRegister register, String name, Properties stalkProperties, Properties flowerProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var plantBlock = ChorusStalks.create(register, name, stalkProperties, creativeTab);
        var flowerBlock = ChorusFlowers.create(register, name, flowerProperties, plantBlock, creativeTab);

        return new BranchingPlantDefinition(name, plantBlock, flowerBlock);
    }

    private ChorusPlants() {
        // Prevent instantiation
    }
}
