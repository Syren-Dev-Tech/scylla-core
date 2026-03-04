package com.github.syren_dev_tech.scylla.registry;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;

public class ItemRegistry {

    private final ModRegister registry;
    private final IRegistrar<Item> registrar;

    public final Map<String, ItemDefinition<? extends Item>> items = new HashMap<>();
    public final Map<String, ItemDefinition<? extends Item>> tools = new HashMap<>();
    public final Map<String, ItemDefinition<? extends Item>> weapons = new HashMap<>();
    public final Map<String, ItemDefinition<? extends ArmorItem>> wearable = new HashMap<>();
    public final Map<String, ItemDefinition<? extends Item>> foods = new HashMap<>();

    public final <T extends Item> ItemDefinition<T> register(String name, Supplier<T> item) {
        var newItem = registrar.register(registry.modId, name, item);
        var def = new ItemDefinition<>(registry, name, newItem);
        this.items.put(name, def);

        if (ScyllaCommon.LOGGER.isInfoEnabled()) {
            ScyllaCommon.LOGGER.info(String.format("Registered new item (not in creative tab): %s", name));
        }

        return def;
    }

    public final <T extends Item> ItemDefinition<T> register(String name, Supplier<T> item, ResourceKey<CreativeModeTab> creativeTab) {
        var newItem = this.registrar.register(registry.modId, name, item);
        var def = new ItemDefinition<>(registry, name, newItem);
        this.items.put(name, def);

        this.registry.creativeTabRegistry.useCreativeTab(creativeTab, newItem);

        if (ScyllaCommon.LOGGER.isInfoEnabled()) {
            ScyllaCommon.LOGGER.info(String.format("Registered new item: %s:%s", this.registry.modId, name));
        }

        return def;
    }

    public <X> void finish(X bus) {
        this.registrar.finish(bus);
    }

    public ItemRegistry(ModRegister registry, IRegistrar<Item> registrar) {
        this.registry = registry;
        this.registrar = registrar;
    }
}
