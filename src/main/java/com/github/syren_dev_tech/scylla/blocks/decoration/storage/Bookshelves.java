package com.github.syren_dev_tech.scylla.blocks.decoration.storage;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Bookshelves {

    public static BlockDefinition<ChiseledBookShelfBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.CHISELED_BOOKSHELF));
    }

    public static BlockDefinition<ChiseledBookShelfBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.CHISELED_BOOKSHELF), creativeTab);
    }

    public static BlockDefinition<ChiseledBookShelfBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChiseledBookShelfBlock(properties)));
    }

    public static BlockDefinition<ChiseledBookShelfBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new ChiseledBookShelfBlock(properties)), creativeTab);
    }

    private Bookshelves() {
        // Prevent instantiation
    }
}
