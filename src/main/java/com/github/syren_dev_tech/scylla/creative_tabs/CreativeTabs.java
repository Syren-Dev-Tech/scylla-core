package com.github.syren_dev_tech.scylla.creative_tabs;

import com.github.syren_dev_tech.scylla.registry.definitions.CreativeTabDefinition;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.world.item.Item;

public class CreativeTabs {

    public static final CreativeTabDefinition create(ModRegister register, String name, ItemDefinition<? extends Item> item) {
        return register.creativeTabRegistry.register(name, item);
    }

    private CreativeTabs() {
        // Prevent instantiation
    }
}
