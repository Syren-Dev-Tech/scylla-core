package com.github.syren_dev_tech.scylla.common.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Hoes {

    private Hoes() {}

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name) {
        return create(register, name, new Properties(), Tiers.IRON);
    }

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Properties(), Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, Tiers.IRON);
    }

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name, Properties properties, Tier tier) {
        var hoe = register.itemRegistry.register(name, () -> new HoeItem(tier, properties));
        register.itemRegistry.tools.put(name, hoe);

        return hoe;
    }

    public static final ItemDefinition<HoeItem> create(ModRegister register, String name, Properties properties, Tier tier, ResourceKey<CreativeModeTab> creativeTab) {
        var hoe = register.itemRegistry.register(name, () -> new HoeItem(tier, properties), creativeTab);
        register.itemRegistry.tools.put(name, hoe);

        return hoe;
    }
}
