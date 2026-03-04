package com.github.syren_dev_tech.scylla.common.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.TowerPlantDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Kelp {
    public static class KelpStalks {

        public static final BlockDefinition<KelpPlantBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.KELP_PLANT));
        }

        public static final BlockDefinition<KelpPlantBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.KELP_PLANT), creativeTab);
        }

        public static final BlockDefinition<KelpPlantBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new KelpPlantBlock(properties)));
        }

        public static final BlockDefinition<KelpPlantBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new KelpPlantBlock(properties)), creativeTab);
        }

        private KelpStalks() {
            // Prevent instantiation
        }
    }

    public static class KelpTops {

        public static final BlockDefinition<KelpBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.KELP));
        }

        public static final BlockDefinition<KelpBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.KELP), creativeTab);
        }

        public static final BlockDefinition<KelpBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new KelpBlock(properties)));
        }

        public static final BlockDefinition<KelpBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new KelpBlock(properties)), creativeTab);
        }

        private KelpTops() {
            // Prevent instantiation
        }
    }

    public static final TowerPlantDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.KELP), Properties.ofFullCopy(Blocks.KELP_PLANT));
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.KELP), Properties.ofFullCopy(Blocks.KELP_PLANT), creativeTab);
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, Properties kelpProperties, Properties kelpPlantProperties) {
        var stalk = KelpTops.create(register, name + "_plant", kelpPlantProperties);
        var top = KelpStalks.create(register, name, kelpProperties);

        return new TowerPlantDefinition(name, top, stalk);
    }

    public static final TowerPlantDefinition create(ModRegister register, String name, Properties kelpProperties, Properties kelpPlantProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var stalk = KelpTops.create(register, name + "_plant", kelpPlantProperties, creativeTab);
        var top = KelpStalks.create(register, name, kelpProperties, creativeTab);

        return new TowerPlantDefinition(name, top, stalk);
    }

    private Kelp() {
        // Prevent instantiation
    }
}
