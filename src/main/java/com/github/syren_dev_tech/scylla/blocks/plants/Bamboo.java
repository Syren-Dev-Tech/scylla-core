package com.github.syren_dev_tech.scylla.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.utilities.collections.Tuple;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Bamboo {
    public static class BambooStalks {

        public static BlockDefinition<BambooStalkBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO));
        }

        public static BlockDefinition<BambooStalkBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO), creativeTab);
        }

        public static BlockDefinition<BambooStalkBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BambooStalkBlock(properties)));
        }

        public static BlockDefinition<BambooStalkBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BambooStalkBlock(properties)), creativeTab);
        }

        private BambooStalks() {
            // Prevent instantiation
        }
    }

    public static class BambooSaplings {

        public static BlockDefinition<BambooSaplingBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO_SAPLING));
        }

        public static BlockDefinition<BambooSaplingBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO_SAPLING), creativeTab);
        }

        public static BlockDefinition<BambooSaplingBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BambooSaplingBlock(properties)));
        }

        public static BlockDefinition<BambooSaplingBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BambooSaplingBlock(properties)), creativeTab);
        }

        private BambooSaplings() {
            // Prevent instantiation
        }
    }

    public static final Tuple<BlockDefinition<BambooStalkBlock>, BlockDefinition<BambooSaplingBlock>> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO), Properties.ofFullCopy(Blocks.BAMBOO_SAPLING));
    }

    public static final Tuple<BlockDefinition<BambooStalkBlock>, BlockDefinition<BambooSaplingBlock>> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.BAMBOO), Properties.ofFullCopy(Blocks.BAMBOO_SAPLING), creativeTab);
    }

    public static final Tuple<BlockDefinition<BambooStalkBlock>, BlockDefinition<BambooSaplingBlock>> create(ModRegister register, String name, Properties stalkProperties, Properties saplingProperties) {
        return new Tuple<>(BambooStalks.create(register, name, stalkProperties), BambooSaplings.create(register, name, saplingProperties));
    }

    public static final Tuple<BlockDefinition<BambooStalkBlock>, BlockDefinition<BambooSaplingBlock>> create(ModRegister register, String name, Properties stalkProperties, Properties saplingProperties, ResourceKey<CreativeModeTab> creativeTab) {
        return new Tuple<>(BambooStalks.create(register, name, stalkProperties, creativeTab), BambooSaplings.create(register, name, saplingProperties, creativeTab));
    }

    private Bamboo() {
        // Prevent instantiation
    }
}
