package com.github.syren_dev_tech.scylla.registry;

import net.minecraft.resources.ResourceKey;
import java.util.HashMap;
import java.util.Map;
import com.github.syren_dev_tech.scylla.registry.definitions.CreativeTabDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class CreativeTabRegistry {

    private final ModRegister registry;
    private final IRegistrar<CreativeModeTab> registrar;

    public final Map<String, CreativeTabDefinition> tabs = new HashMap<>();

    public final CreativeTabDefinition register(String name, ItemDefinition<? extends Item> item) {
        return this.tabs.computeIfAbsent(name, k -> {
            var definition = new CreativeTabDefinition(registry, name, item);
            definition.addItem(item);
            this.registrar.register(this.registry.modId, name, definition.registry);
            return definition;
        });
    }

    public <T extends Item> ItemDefinition<T> useCreativeTab(String tab, ItemDefinition<T> item) {
        this.tabs.computeIfAbsent(tab, k -> {
            var definition = new CreativeTabDefinition(registry, tab, item);
            this.registrar.register(this.registry.modId, tab, definition.registry);
            return definition;
        }).addItem(item);

        return item;
    }

    public <T extends Item> ItemDefinition<T> useCreativeTab(ResourceKey<CreativeModeTab> tab, ItemDefinition<T> item) {
        var tabName = tab.location().getPath();
        this.tabs.computeIfAbsent(tabName, k -> {
            var definition = new CreativeTabDefinition(registry, tabName, item);
            this.registrar.register(this.registry.modId, tabName, definition.registry);
            return definition;
        }).addItem(item);

        return item;
    }

    public CreativeTabDefinition getCreativeTab(String name) {
        return this.tabs.get(name);
    }

    public <X> void finish(X bus) {
        this.registrar.finish(bus);
    }

    public CreativeTabRegistry(ModRegister registry, IRegistrar<CreativeModeTab> registrar) {
        this.registry = registry;
        this.registrar = registrar;
    }
}
