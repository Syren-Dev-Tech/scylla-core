package com.github.syren_dev_tech.scylla.common.crops;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.StemDefinition;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.core.registries.Registries;


public class Stems {

    private Stems() {}

    public static class AttachedStems {

        private AttachedStems() {}

        public static final BlockDefinition<AttachedStemBlock> create(ModRegister register, String name, ResourceKey<Block> stem, ResourceKey<Block> fruit, ResourceKey<Item> seed) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new AttachedStemBlock(stem, fruit, seed, Properties.ofFullCopy(Blocks.PUMPKIN_STEM))));
        }

        public static final BlockDefinition<AttachedStemBlock> create(ModRegister register, String name, ResourceKey<Block> stem, ResourceKey<Block> fruit, ResourceKey<Item> seed, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new AttachedStemBlock(stem, fruit, seed, properties)));
        }
    }

    public static class GrowingStems {

        private GrowingStems() {}

        public static final BlockDefinition<StemBlock> create(ModRegister register, String name, ResourceKey<Block> fruit, ResourceKey<Block> attachedStem, ResourceKey<Item> seed) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new StemBlock(fruit, attachedStem, seed, Properties.ofFullCopy(Blocks.PUMPKIN_STEM))));
        }

        public static final BlockDefinition<StemBlock> create(ModRegister register, String name, ResourceKey<Block> fruit, ResourceKey<Block> attachedStem, ResourceKey<Item> seed, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new StemBlock(fruit, attachedStem, seed, properties)));
        }
    }

    public static final StemDefinition create(ModRegister register, String name, ResourceKey<Block> fruit, ResourceKey<Item> seed) {
        ResourceKey<Block> stemKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get());
        ResourceKey<Block> attachedStemKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name + "_attached").get());

        var stem = GrowingStems.create(register, name, fruit, attachedStemKey, seed);
        var attachedStem = AttachedStems.create(register, name, stemKey, fruit, seed);

        return new StemDefinition(stem, attachedStem, stemKey, attachedStemKey);
    }

    public static final StemDefinition create(ModRegister register, String name, ResourceKey<Block> fruit, ResourceKey<Item> seed, Properties properties) {
        ResourceKey<Block> stemKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get());
        ResourceKey<Block> attachedStemKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name + "_attached").get());

        var stem = GrowingStems.create(register, name, fruit, attachedStemKey, seed, properties);
        var attachedStem = AttachedStems.create(register, name, stemKey, fruit, seed, properties);

        return new StemDefinition(stem, attachedStem, stemKey, attachedStemKey);
    }
}
