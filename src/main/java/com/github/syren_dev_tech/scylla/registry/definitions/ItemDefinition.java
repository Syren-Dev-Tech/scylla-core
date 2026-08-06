package com.github.syren_dev_tech.scylla.registry.definitions;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ItemDefinition<T extends Item> {
    public final String name;
    public final Supplier<T> registry;
    public final ResourceKey<Item> resourceKey;

    public ItemDefinition(ModRegister modRegister, String name, Supplier<T> registry) {
        this.registry = registry;

        if (modRegister == null) {
            this.name = null;
            this.resourceKey = null;

            return;
        }

        this.name = name;
        this.resourceKey = ResourceKey.create(Registries.ITEM, new ResourcePath(modRegister.modId, name).get());
    }

    public static <T extends Item> ItemDefinition<T> of(Supplier<T> item) {
        return new ItemDefinition<>(null, "", item);
    }
}
