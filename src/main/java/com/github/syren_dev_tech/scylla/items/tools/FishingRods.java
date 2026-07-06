package com.github.syren_dev_tech.scylla.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item.Properties;

public class FishingRods {

    private FishingRods() {}

    public static final ItemDefinition<FishingRodItem> create(ModRegister register, String name) {
        return create(register, name, new Properties());
    }

    public static final ItemDefinition<FishingRodItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Properties(), creativeTab);
    }

    public static final ItemDefinition<FishingRodItem> create(ModRegister register, String name, Properties properties) {
        var fas = register.itemRegistry.register(name, () -> new FishingRodItem(properties));
        register.itemRegistry.tools.put(name, fas);

        return fas;
    }

    public static final ItemDefinition<FishingRodItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var fas = register.itemRegistry.register(name, () -> new FishingRodItem(properties), creativeTab);
        register.itemRegistry.tools.put(name, fas);

        return fas;
    }
}
