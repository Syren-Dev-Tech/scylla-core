package com.github.syren_dev_tech.scylla.blocks.decoration;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class StickyBlocks {
    public static class SlimeBlocks {

        public static BlockDefinition<SlimeBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.SLIME_BLOCK));
        }

        public static BlockDefinition<SlimeBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.SLIME_BLOCK), creativeTab);
        }

        public static BlockDefinition<SlimeBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new SlimeBlock(properties)));
        }

        public static BlockDefinition<SlimeBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new SlimeBlock(properties)), creativeTab);
        }

        private SlimeBlocks() {
            // Prevent instantiation
        }
    }

    public static class HoneyBlocks {

        public static BlockDefinition<HoneyBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.HONEY_BLOCK));
        }

        public static BlockDefinition<HoneyBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.HONEY_BLOCK), creativeTab);
        }

        public static BlockDefinition<HoneyBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new HoneyBlock(properties)));
        }

        public static BlockDefinition<HoneyBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new HoneyBlock(properties)), creativeTab);
        }

        private HoneyBlocks() {
            // Prevent instantiation
        }
    }

    private StickyBlocks() {
        // Prevent instantiation
    }
}
