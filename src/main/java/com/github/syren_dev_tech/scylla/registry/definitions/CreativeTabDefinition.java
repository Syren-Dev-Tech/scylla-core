package com.github.syren_dev_tech.scylla.registry.definitions;

import java.util.function.Supplier;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class CreativeTabDefinition {
    public final String name;
    public final ResourceKey<CreativeModeTab> key;
    public final ItemDefinition<?> item;
    public final Supplier<CreativeModeTab> registry;
    private final Map<String, ItemDefinition<?>> tabItems = new HashMap<>();

    public CreativeTabDefinition(ModRegister register, String name, ItemDefinition<?> item) {
        this.name = name;
        this.item = item;
        this.key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourcePath(register.modId, name).get());

        var builder = CreativeModeTab.builder();
        builder.icon(() -> item.registry.get().getDefaultInstance());
        builder.title(Component.translatable(this.key.location().toString()));
        builder.displayItems((params, output) -> {
            for (var tabItem : this.tabItems.values()) {
                output.accept(tabItem.registry.get().getDefaultInstance());
            }
        });

        this.registry = builder::build;
    }

    public void addItem(ItemDefinition<?> item) {
        this.tabItems.put(item.name, item);
    }

    public List<ItemDefinition<?>> getItems() {
        return new ArrayList<>(this.tabItems.values());
    }
}
