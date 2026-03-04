package com.github.syren_dev_tech.scylla.common.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Axes {

    private Axes() {}

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name) {
        return create(register, name, new Properties(), Tiers.IRON);
    }

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Properties(), Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, Tiers.IRON);
    }

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name, Properties properties, Tier tier) {
        var axe = register.itemRegistry.register(name, () -> new AxeItem(tier, properties));
        register.itemRegistry.tools.put(name, axe);

        return axe;
    }

    public static final ItemDefinition<AxeItem> create(ModRegister register, String name, Properties properties, Tier tier, ResourceKey<CreativeModeTab> creativeTab) {
        var axe = register.itemRegistry.register(name, () -> new AxeItem(tier, properties), creativeTab);
        register.itemRegistry.tools.put(name, axe);

        return axe;
    }
}
