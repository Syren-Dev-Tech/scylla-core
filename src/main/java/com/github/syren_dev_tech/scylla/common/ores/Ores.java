package com.github.syren_dev_tech.scylla.common.ores;

import java.util.function.Supplier;

import com.github.syren_dev_tech.scylla.common.items.ModItem;
import com.github.syren_dev_tech.scylla.common.ores.types.Ore;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import com.github.syren_dev_tech.scylla.common.registry.features.OreFeature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// RedstoneOre

public class Ores {

    private Ores() {}

    public class GemOre {

        private GemOre() {}

        public static final void create(ModRegister register, String name, IntProvider xpRange, ResourceKey<CreativeModeTab> creativeTabItem, ResourceKey<CreativeModeTab> creativeTabBlock) {
            ModItem.create(register, name, creativeTabItem);
            Ores.create(register, name + "_ore", xpRange, creativeTabBlock);
        }
    }

    public class MetalOre {

        private MetalOre() {}

        public static final void create(ModRegister register, String name, IntProvider xpRange, Item.Properties ingotProperties, ResourceKey<CreativeModeTab> creativeTabItem, ResourceKey<CreativeModeTab> creativeTabBlock) {
            ModItem.create(register, name + "_ingot", ingotProperties, creativeTabItem);
            ModItem.create(register, name + "_nugget", ingotProperties, creativeTabItem);
            Ores.create(register, name + "_ore", xpRange, creativeTabBlock);
        }
    }

    public static final Supplier<Ore> create(ModRegister register, String name, IntProvider xpRange) {
        return create(register, name, xpRange, Properties.ofFullCopy(Blocks.IRON_ORE));
    }

    public static final Supplier<Ore> create(ModRegister register, String name, IntProvider xpRange, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, xpRange, Properties.ofFullCopy(Blocks.IRON_ORE), creativeTab);
    }

    public static final Supplier<Ore> create(ModRegister register, String name, IntProvider xpRange, Properties properties) {
        var ore = register.blockRegistry.register(name, () -> new Ore(xpRange, properties.requiresCorrectToolForDrops()));
        OreFeature.register(name, ore.get());

        return ore;
    }

    public static final Supplier<Ore> create(ModRegister register, String name, IntProvider xpRange, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var ore = register.blockRegistry.register(name, () -> new Ore(xpRange, properties.requiresCorrectToolForDrops()), creativeTab);
        OreFeature.register(name, ore.get());

        return ore;
    }
}
