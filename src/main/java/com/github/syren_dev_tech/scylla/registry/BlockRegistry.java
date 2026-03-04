package com.github.syren_dev_tech.scylla.registry;

import java.util.HashMap;
import java.util.Map;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlockRegistry {

    private final ModRegister registry;
    private final IRegistrar<Block> registrar;

    public final Map<String, BlockDefinition<? extends Block>> blocks = new HashMap<>();
    public final Map<String, BlockDefinition<? extends Block>> transparentBlocks = new HashMap<>();

    public final <T extends Block> BlockDefinition<T> register(String name, BlockDefinition<T> block) {
        var newBlock = this.registrar.register(registry.modId, name, block.registry);
        var def = new BlockDefinition<>(registry, name, newBlock);
        this.blocks.put(name, def);

        return def;
    }

    public final <T extends Block> BlockDefinition<T> register(String name, BlockDefinition<T> block, ResourceKey<CreativeModeTab> creativeTab) {
        var newBlock = this.registrar.register(registry.modId, name, block.registry);
        var def = new BlockDefinition<>(registry, name, newBlock);
        this.blocks.put(name, def);

        this.registry.itemRegistry.register(name, () -> new BlockItem(newBlock.get(), new Item.Properties()), creativeTab);

        return def;
    }

    public <X> void finish(X bus) {
        this.registrar.finish(bus);
    }

    public BlockRegistry(ModRegister registry, IRegistrar<Block> registrar) {
        this.registry = registry;
        this.registrar = registrar;
    }
}
