package com.github.syren_dev_tech.scylla.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ShearsItem;

public class Shears {

    private Shears() {}

    public static final ItemDefinition<ShearsItem> create(ModRegister register, String name) {
        return create(register, name, new Item.Properties());
    }

    public static final ItemDefinition<ShearsItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Item.Properties(), creativeTab);
    }

    public static final ItemDefinition<ShearsItem> create(ModRegister register, String name, Properties properties) {
        var shears = register.itemRegistry.register(name, () -> new ShearsItem(properties));
        register.itemRegistry.tools.put(name, shears);

        return shears;
    }

    public static final ItemDefinition<ShearsItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var shears = register.itemRegistry.register(name, () -> new ShearsItem(properties), creativeTab);
        register.itemRegistry.tools.put(name, shears);

        return shears;
    }
}
