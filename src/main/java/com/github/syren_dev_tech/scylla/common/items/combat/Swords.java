package com.github.syren_dev_tech.scylla.common.items.combat;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Swords {

    private Swords() {}

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name) {
        return create(register, name, new Item.Properties());
    }

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Item.Properties(), creativeTab);
    }

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, Tiers.IRON);
    }

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name, Properties properties, Tier tier) {
        var sword = register.itemRegistry.register(name, () -> new SwordItem(tier, properties));
        register.itemRegistry.weapons.put(name, sword);

        return sword;
    }

    public static final ItemDefinition<SwordItem> create(ModRegister register, String name, Properties properties, Tier tier, ResourceKey<CreativeModeTab> creativeTab) {
        var sword = register.itemRegistry.register(name, () -> new SwordItem(tier, properties), creativeTab);
        register.itemRegistry.weapons.put(name, sword);

        return sword;
    }
}
