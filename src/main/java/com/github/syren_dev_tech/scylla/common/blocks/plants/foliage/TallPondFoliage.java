package com.github.syren_dev_tech.scylla.common.blocks.plants.foliage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.TallPondFoliageDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.BigDripleafStemBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Needs custom implementation...

public class TallPondFoliage {
    public static class TallPondFoliageLeaves {

        public static final BlockDefinition<BigDripleafBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF));
        }

        public static final BlockDefinition<BigDripleafBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF), creativeTab);
        }

        public static final BlockDefinition<BigDripleafBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BigDripleafBlock(properties)));
        }

        public static final BlockDefinition<BigDripleafBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BigDripleafBlock(properties)), creativeTab);
        }

        private TallPondFoliageLeaves() {
            // Prevent instantiation
        }
    }

    public static class TallPondFoliageStems {

        public static final BlockDefinition<BigDripleafStemBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF_STEM));
        }

        public static final BlockDefinition<BigDripleafStemBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF_STEM), creativeTab);
        }

        public static final BlockDefinition<BigDripleafStemBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BigDripleafStemBlock(properties)));
        }

        public static final BlockDefinition<BigDripleafStemBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BigDripleafStemBlock(properties)), creativeTab);
        }

        private TallPondFoliageStems() {
            // Prevent instantiation
        }
    }

    public static final TallPondFoliageDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF_STEM));
    }

    public static final TallPondFoliageDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.BIG_DRIPLEAF_STEM), creativeTab);
    }

    public static final TallPondFoliageDefinition create(ModRegister register, String name, Properties properties) {
        var leaves = TallPondFoliageLeaves.create(register, name, properties);
        var stem = TallPondFoliageStems.create(register, name, properties);

        return new TallPondFoliageDefinition(name, leaves, stem);
    }

    public static final TallPondFoliageDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var leaves = TallPondFoliageLeaves.create(register, name, properties, creativeTab);
        var stem = TallPondFoliageStems.create(register, name, properties);

        return new TallPondFoliageDefinition(name, leaves, stem);
    }

    private TallPondFoliage() {
        // Prevent instantiation
    }
}
