package com.github.syren_dev_tech.scylla.blocks.misc;

import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.ConcreteDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Concrete {
    public static class ConcretePowders {

        public static BlockDefinition<ConcretePowderBlock> create(ModRegister register, String name, BlockDefinition<Block> hardenedBlock) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA), hardenedBlock);
        }

        public static BlockDefinition<ConcretePowderBlock> create(ModRegister register, String name, BlockDefinition<Block> hardenedBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA), hardenedBlock, creativeTab);
        }

        public static BlockDefinition<ConcretePowderBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> hardenedBlock) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ConcretePowderBlock(hardenedBlock.registry.get(), properties)));
        }

        public static BlockDefinition<ConcretePowderBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<Block> hardenedBlock, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new ConcretePowderBlock(hardenedBlock.registry.get(), properties)), creativeTab);
        }

        private ConcretePowders() {
            // Prevent instantiation
        }
    }

    public static final ConcreteDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CONCRETE), Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER));
    }

    public static final ConcreteDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CONCRETE), Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER), creativeTab);
    }

    public static final ConcreteDefinition create(ModRegister register, String name, Properties hardenedProperties, Properties powderProperties) {
        var solid = ModBlocks.create(register, name, hardenedProperties);
        var powder = ConcretePowders.create(register, name, powderProperties, solid);

        return new ConcreteDefinition(name, powder, solid);
    }

    public static final ConcreteDefinition create(ModRegister register, String name, Properties hardenedProperties, Properties powderProperties, ResourceKey<CreativeModeTab> creativeTab) {
        var solid = ModBlocks.create(register, name, hardenedProperties, creativeTab);
        var powder = ConcretePowders.create(register, name, powderProperties, solid, creativeTab);

        return new ConcreteDefinition(name, powder, solid);
    }

    private Concrete() {
        // Prevent instantiation
    }
}
