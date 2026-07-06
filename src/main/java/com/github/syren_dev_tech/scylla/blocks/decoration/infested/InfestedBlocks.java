package com.github.syren_dev_tech.scylla.blocks.decoration.infested;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class InfestedBlocks {

    public static BlockDefinition<InfestedBlock> create(ModRegister register, String name, BlockDefinition<Block> hostBlock) {
        return create(register, name, hostBlock, Properties.ofFullCopy(hostBlock.registry.get()));
    }

    public static BlockDefinition<InfestedBlock> create(ModRegister register, String name, BlockDefinition<Block> hostBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, hostBlock, Properties.ofFullCopy(hostBlock.registry.get()), creativeTab);
    }

    public static BlockDefinition<InfestedBlock> create(ModRegister register, String name, BlockDefinition<Block> hostBlock, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new InfestedBlock(hostBlock.registry.get(), properties)));
    }

    public static BlockDefinition<InfestedBlock> create(ModRegister register, String name, BlockDefinition<Block> hostBlock, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new InfestedBlock(hostBlock.registry.get(), properties)), creativeTab);
    }

    private InfestedBlocks() {
        // Prevent instantiation
    }
}
