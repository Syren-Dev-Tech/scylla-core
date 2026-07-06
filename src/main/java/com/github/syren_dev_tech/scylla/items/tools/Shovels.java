package com.github.syren_dev_tech.scylla.items.tools;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class Shovels {

    private Shovels() {}

    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name) {
        return create(register, name, new Item.Properties());
    }

    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, new Item.Properties(), creativeTab);
    }

    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, Tiers.IRON);
    }

    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, Tiers.IRON, creativeTab);
    }


    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name, Properties properties, Tier tier) {
        var shovel = register.itemRegistry.register(name, () -> new ShovelItem(tier, properties));
        register.itemRegistry.tools.put(name, shovel);

        return shovel;
    }

    public static final ItemDefinition<ShovelItem> create(ModRegister register, String name, Properties properties, Tier tier, ResourceKey<CreativeModeTab> creativeTab) {
        var shovel = register.itemRegistry.register(name, () -> new ShovelItem(tier, properties), creativeTab);
        register.itemRegistry.tools.put(name, shovel);

        return shovel;
    }
}
