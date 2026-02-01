package com.github.syren_dev_tech.scylla.common.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class BlockRegistry {

    private final ModRegister registry;
    private final IRegistrar<Block> registrar;

    public final Map<String, Supplier<? extends Block>> blocks = new HashMap<>();
    public final Map<String, Supplier<? extends Block>> transparentBlocks = new HashMap<>();

    public final <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
        var newBlock = this.registrar.register(registry.modId, name, block);
        this.blocks.put(name, newBlock);

        return newBlock;
    }

    public final <T extends Block> Supplier<T> register(String name, Supplier<T> block, ResourceKey<CreativeModeTab> creativeTab) {
        var newBlock = this.registrar.register(registry.modId, name, block);
        this.blocks.put(name, newBlock);

        this.registry.itemRegistry.register(name, () -> new BlockItem(newBlock.get(), new Item.Properties()), creativeTab);

        return newBlock;
    }

    public <X> void finish(X bus) {
        this.registrar.finish(bus);
    }

    public BlockRegistry(ModRegister registry, IRegistrar<Block> registrar) {
        this.registry = registry;
        this.registrar = registrar;
    }
}
