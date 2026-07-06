package com.github.syren_dev_tech.scylla.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Pickaxes {

    private Pickaxes() {}

    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name) {
        return create(register, name, new Item.Properties(), Tiers.IRON);
    }

    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Item.Properties(), Tiers.IRON, creativeTab);
    }

    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, Tiers.IRON);
    }

    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, Tiers.IRON, creativeTab);
    }


    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name, Properties properties, Tier tier) {
        var pickaxe = register.itemRegistry.register(name, () -> new PickaxeItem(tier, properties));
        register.itemRegistry.tools.put(name, pickaxe);

        return pickaxe;
    }

    public static final ItemDefinition<PickaxeItem> create(ModRegister register, String name, Properties properties, Tier tier, ResourceKey<CreativeModeTab> creativeTab) {
        var pickaxe = register.itemRegistry.register(name, () -> new PickaxeItem(tier, properties), creativeTab);
        register.itemRegistry.tools.put(name, pickaxe);

        return pickaxe;
    }
}
