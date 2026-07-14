package com.github.syren_dev_tech.scylla.ores;

import java.util.Objects;
import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.items.combat.Swords;
import com.github.syren_dev_tech.scylla.items.combat.Wearables;
import com.github.syren_dev_tech.scylla.items.ModItem;
import com.github.syren_dev_tech.scylla.items.tools.Axes;
import com.github.syren_dev_tech.scylla.items.tools.Hoes;
import com.github.syren_dev_tech.scylla.items.tools.Pickaxes;
import com.github.syren_dev_tech.scylla.items.tools.Shovels;
import com.github.syren_dev_tech.scylla.ores.types.Ore;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.ItemDefinition;
import com.github.syren_dev_tech.scylla.registry.features.OreFeature;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Ores {

    private Ores() {}

    public static GemBuilder gem(String name) {
        return new GemBuilder(name);
    }

    public static DustBuilder dust(String name) {
        return new DustBuilder(name);
    }

    public static MetalBuilder metal(String name) {
        return new MetalBuilder(name);
    }

    public record ToolSet(ItemDefinition<?> sword, ItemDefinition<?> pickaxe, ItemDefinition<?> axe, ItemDefinition<?> shovel, ItemDefinition<?> hoe) {
    }

    public record ArmorSet(ItemDefinition<ArmorItem> helmet, ItemDefinition<ArmorItem> chestplate, ItemDefinition<ArmorItem> leggings, ItemDefinition<ArmorItem> boots) {
    }

    public record GemDefinition(String materialName, BlockDefinition<Ore> ore, ItemDefinition<Item> gem, BlockDefinition<Block> gemBlock, ToolSet toolSet, ArmorSet armorSet) {
    }

    public record DustDefinition(String materialName, BlockDefinition<Ore> ore, ItemDefinition<Item> dust, BlockDefinition<Block> dustBlock) {
    }

    public record MetalDefinition(String materialName, BlockDefinition<Ore> ore, ItemDefinition<Item> rawMetal, ItemDefinition<Item> ingot, ItemDefinition<Item> nugget, BlockDefinition<Block> rawBlock, BlockDefinition<Block> metalBlock, ToolSet toolSet, ArmorSet armorSet) {
    }

    private abstract static class BaseBuilder<T extends BaseBuilder<T>> {

        protected final String name;
        protected String oreName;
        protected IntProvider xpRange = ConstantInt.of(0);
        protected Properties oreProperties = Properties.ofFullCopy(Blocks.IRON_ORE);
        protected ResourceKey<CreativeModeTab> itemCreativeTab;
        protected ResourceKey<CreativeModeTab> blockCreativeTab;

        protected BaseBuilder(String name) {
            this.name = requireName(name, "Material name");
            this.oreName = this.name + "_ore";
        }

        protected abstract T self();

        public T withOreName(String oreName) {
            this.oreName = requireName(oreName, "Ore block name");
            return self();
        }

        public T withXpRange(IntProvider xpRange) {
            this.xpRange = Objects.requireNonNull(xpRange, "XP range cannot be null");
            return self();
        }

        public T withOreProperties(Properties properties) {
            this.oreProperties = Objects.requireNonNull(properties, "Ore properties cannot be null");
            return self();
        }

        public T withItemCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.itemCreativeTab = creativeTab;
            return self();
        }

        public T withBlockCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.blockCreativeTab = creativeTab;
            return self();
        }

        protected BlockDefinition<Ore> registerOre(ModRegister register) {
            return create(register, this.oreName, this.xpRange, this.oreProperties, this.blockCreativeTab);
        }

        protected ItemDefinition<Item> registerItem(ModRegister register, String itemName, Item.Properties properties) {
            if (this.itemCreativeTab == null) {
                return ModItem.create(register, itemName, properties);
            }

            return ModItem.create(register, itemName, properties, this.itemCreativeTab);
        }

        protected BlockDefinition<Block> registerStorageBlock(ModRegister register, String blockName, Properties properties) {
            if (this.blockCreativeTab == null) {
                return ModBlocks.create(register, blockName, properties);
            }

            return ModBlocks.create(register, blockName, properties, this.blockCreativeTab);
        }
    }

    private abstract static class GearCapableBuilder<T extends GearCapableBuilder<T>> extends BaseBuilder<T> {

        protected Tier toolTier;
        protected Holder<ArmorMaterial> armorMaterial;
        protected boolean registerToolSet;
        protected boolean registerArmorSet;
        protected Item.Properties toolProperties = new Item.Properties();
        protected Item.Properties armorProperties = new Item.Properties();
        protected ResourceKey<CreativeModeTab> gearCreativeTab;

        protected GearCapableBuilder(String name) {
            super(name);
        }

        public T withToolTier(Tier toolTier) {
            this.toolTier = Objects.requireNonNull(toolTier, "Tool tier cannot be null");
            this.registerToolSet = true;
            return self();
        }

        public T withToolProperties(Item.Properties toolProperties) {
            this.toolProperties = Objects.requireNonNull(toolProperties, "Tool properties cannot be null");
            return self();
        }

        public T enableToolSet(boolean enabled) {
            this.registerToolSet = enabled;
            return self();
        }

        public T withArmorMaterial(Holder<ArmorMaterial> armorMaterial) {
            this.armorMaterial = Objects.requireNonNull(armorMaterial, "Armor material cannot be null");
            this.registerArmorSet = true;
            return self();
        }

        public T withArmorProperties(Item.Properties armorProperties) {
            this.armorProperties = Objects.requireNonNull(armorProperties, "Armor properties cannot be null");
            return self();
        }

        public T enableArmorSet(boolean enabled) {
            this.registerArmorSet = enabled;
            return self();
        }

        public T withGearCreativeTab(ResourceKey<CreativeModeTab> creativeTab) {
            this.gearCreativeTab = creativeTab;
            return self();
        }

        protected ToolSet registerTools(ModRegister register) {
            if (!this.registerToolSet || this.toolTier == null) {
                return null;
            }

            var tab = this.gearCreativeTab != null ? this.gearCreativeTab : this.itemCreativeTab;
            if (tab == null) {
                return new ToolSet(Swords.create(register, this.name + "_sword", this.toolProperties, this.toolTier), Pickaxes.create(register, this.name + "_pickaxe", this.toolProperties, this.toolTier), Axes.create(register, this.name + "_axe", this.toolProperties, this.toolTier), Shovels.create(register, this.name + "_shovel", this.toolProperties, this.toolTier), Hoes.create(register, this.name + "_hoe", this.toolProperties, this.toolTier));
            }

            return new ToolSet(Swords.create(register, this.name + "_sword", this.toolProperties, this.toolTier, tab), Pickaxes.create(register, this.name + "_pickaxe", this.toolProperties, this.toolTier, tab), Axes.create(register, this.name + "_axe", this.toolProperties, this.toolTier, tab), Shovels.create(register, this.name + "_shovel", this.toolProperties, this.toolTier, tab), Hoes.create(register, this.name + "_hoe", this.toolProperties, this.toolTier, tab));
        }

        protected ArmorSet registerArmor(ModRegister register) {
            if (!this.registerArmorSet || this.armorMaterial == null) {
                return null;
            }

            var tab = this.gearCreativeTab != null ? this.gearCreativeTab : this.itemCreativeTab;
            if (tab == null) {
                return new ArmorSet(Wearables.create(register, this.name + "_helmet", this.armorProperties, ArmorItem.Type.HELMET, this.armorMaterial), Wearables.create(register, this.name + "_chestplate", this.armorProperties, ArmorItem.Type.CHESTPLATE, this.armorMaterial), Wearables.create(register, this.name + "_leggings", this.armorProperties, ArmorItem.Type.LEGGINGS, this.armorMaterial), Wearables.create(register, this.name + "_boots", this.armorProperties, ArmorItem.Type.BOOTS, this.armorMaterial));
            }

            return new ArmorSet(Wearables.create(register, this.name + "_helmet", this.armorProperties, ArmorItem.Type.HELMET, this.armorMaterial, tab), Wearables.create(register, this.name + "_chestplate", this.armorProperties, ArmorItem.Type.CHESTPLATE, this.armorMaterial, tab), Wearables.create(register, this.name + "_leggings", this.armorProperties, ArmorItem.Type.LEGGINGS, this.armorMaterial, tab), Wearables.create(register, this.name + "_boots", this.armorProperties, ArmorItem.Type.BOOTS, this.armorMaterial, tab));
        }
    }

    public static final class GemBuilder extends GearCapableBuilder<GemBuilder> {

        private String gemName;
        private Item.Properties gemProperties = new Item.Properties();
        private String gemBlockName;
        private Properties gemBlockProperties = Properties.ofFullCopy(Blocks.DIAMOND_BLOCK);

        private GemBuilder(String name) {
            super(name);
            this.gemName = name;
            this.gemBlockName = name + "_block";
        }

        @Override
        protected GemBuilder self() {
            return this;
        }

        public GemBuilder withGemName(String gemName) {
            this.gemName = requireName(gemName, "Gem item name");
            return this;
        }

        public GemBuilder withGemProperties(Item.Properties properties) {
            this.gemProperties = Objects.requireNonNull(properties, "Gem item properties cannot be null");
            return this;
        }

        public GemBuilder withGemBlockName(String gemBlockName) {
            this.gemBlockName = requireName(gemBlockName, "Gem block name");
            return this;
        }

        public GemBuilder withGemBlockProperties(Properties properties) {
            this.gemBlockProperties = Objects.requireNonNull(properties, "Gem block properties cannot be null");
            return this;
        }

        public GemDefinition register(ModRegister register) {
            Objects.requireNonNull(register, "Register cannot be null");

            var ore = registerOre(register);
            var gem = registerItem(register, this.gemName, this.gemProperties);
            var gemBlock = registerStorageBlock(register, this.gemBlockName, this.gemBlockProperties);

            return new GemDefinition(this.name, ore, gem, gemBlock, registerTools(register), registerArmor(register));
        }
    }

    public static final class DustBuilder extends BaseBuilder<DustBuilder> {

        private String dustName;
        private Item.Properties dustProperties = new Item.Properties();
        private String dustBlockName;
        private Properties dustBlockProperties = Properties.ofFullCopy(Blocks.REDSTONE_BLOCK);

        private DustBuilder(String name) {
            super(name);
            this.dustName = name + "_dust";
            this.dustBlockName = name + "_block";
        }

        @Override
        protected DustBuilder self() {
            return this;
        }

        public DustBuilder withDustName(String dustName) {
            this.dustName = requireName(dustName, "Dust item name");
            return this;
        }

        public DustBuilder withDustProperties(Item.Properties properties) {
            this.dustProperties = Objects.requireNonNull(properties, "Dust item properties cannot be null");
            return this;
        }

        public DustBuilder withDustBlockName(String dustBlockName) {
            this.dustBlockName = requireName(dustBlockName, "Dust block name");
            return this;
        }

        public DustBuilder withDustBlockProperties(Properties properties) {
            this.dustBlockProperties = Objects.requireNonNull(properties, "Dust block properties cannot be null");
            return this;
        }

        public DustDefinition register(ModRegister register) {
            Objects.requireNonNull(register, "Register cannot be null");

            var ore = registerOre(register);
            var dust = registerItem(register, this.dustName, this.dustProperties);
            var dustBlock = registerStorageBlock(register, this.dustBlockName, this.dustBlockProperties);

            return new DustDefinition(this.name, ore, dust, dustBlock);
        }
    }

    public static final class MetalBuilder extends GearCapableBuilder<MetalBuilder> {

        private String rawMetalName;
        private String ingotName;
        private String nuggetName;
        private Item.Properties rawMetalProperties = new Item.Properties();
        private Item.Properties ingotProperties = new Item.Properties();
        private Item.Properties nuggetProperties = new Item.Properties();
        private String rawBlockName;
        private String metalBlockName;
        private Properties rawBlockProperties = Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK);
        private Properties metalBlockProperties = Properties.ofFullCopy(Blocks.IRON_BLOCK);

        private MetalBuilder(String name) {
            super(name);
            this.rawMetalName = "raw_" + name;
            this.ingotName = name + "_ingot";
            this.nuggetName = name + "_nugget";
            this.rawBlockName = "raw_" + name + "_block";
            this.metalBlockName = name + "_block";
        }

        @Override
        protected MetalBuilder self() {
            return this;
        }

        public MetalBuilder withRawMetalName(String rawMetalName) {
            this.rawMetalName = requireName(rawMetalName, "Raw metal item name");
            return this;
        }

        public MetalBuilder withIngotName(String ingotName) {
            this.ingotName = requireName(ingotName, "Ingot item name");
            return this;
        }

        public MetalBuilder withNuggetName(String nuggetName) {
            this.nuggetName = requireName(nuggetName, "Nugget item name");
            return this;
        }

        public MetalBuilder withRawMetalProperties(Item.Properties properties) {
            this.rawMetalProperties = Objects.requireNonNull(properties, "Raw metal properties cannot be null");
            return this;
        }

        public MetalBuilder withIngotProperties(Item.Properties properties) {
            this.ingotProperties = Objects.requireNonNull(properties, "Ingot properties cannot be null");
            return this;
        }

        public MetalBuilder withNuggetProperties(Item.Properties properties) {
            this.nuggetProperties = Objects.requireNonNull(properties, "Nugget properties cannot be null");
            return this;
        }

        public MetalBuilder withRawBlockName(String rawBlockName) {
            this.rawBlockName = requireName(rawBlockName, "Raw block name");
            return this;
        }

        public MetalBuilder withMetalBlockName(String metalBlockName) {
            this.metalBlockName = requireName(metalBlockName, "Metal block name");
            return this;
        }

        public MetalBuilder withRawBlockProperties(Properties properties) {
            this.rawBlockProperties = Objects.requireNonNull(properties, "Raw block properties cannot be null");
            return this;
        }

        public MetalBuilder withMetalBlockProperties(Properties properties) {
            this.metalBlockProperties = Objects.requireNonNull(properties, "Metal block properties cannot be null");
            return this;
        }

        public MetalDefinition register(ModRegister register) {
            Objects.requireNonNull(register, "Register cannot be null");

            var ore = registerOre(register);
            var rawMetal = registerItem(register, this.rawMetalName, this.rawMetalProperties);
            var ingot = registerItem(register, this.ingotName, this.ingotProperties);
            var nugget = registerItem(register, this.nuggetName, this.nuggetProperties);
            var rawBlock = registerStorageBlock(register, this.rawBlockName, this.rawBlockProperties);
            var metalBlock = registerStorageBlock(register, this.metalBlockName, this.metalBlockProperties);

            return new MetalDefinition(this.name, ore, rawMetal, ingot, nugget, rawBlock, metalBlock, registerTools(register), registerArmor(register));
        }
    }

    private static String requireName(String value, String label) {
        Objects.requireNonNull(value, label + " cannot be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(label + " cannot be blank");
        }

        return value;
    }

    public static BlockDefinition<Ore> create(ModRegister register, String name, IntProvider xpRange) {
        return create(register, name, xpRange, Properties.ofFullCopy(Blocks.IRON_ORE));
    }

    public static BlockDefinition<Ore> create(ModRegister register, String name, IntProvider xpRange, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, xpRange, Properties.ofFullCopy(Blocks.IRON_ORE), creativeTab);
    }

    public static BlockDefinition<Ore> create(ModRegister register, String name, IntProvider xpRange, Properties properties) {
        var ore = register.blockRegistry.register(name, BlockDefinition.of(() -> new Ore(xpRange, properties.requiresCorrectToolForDrops())));
        OreFeature.register(name, ore.registry.get());

        return ore;
    }

    public static BlockDefinition<Ore> create(ModRegister register, String name, IntProvider xpRange, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        if (creativeTab == null) {
            return create(register, name, xpRange, properties);
        }

        var ore = register.blockRegistry.register(name, BlockDefinition.of(() -> new Ore(xpRange, properties.requiresCorrectToolForDrops())), creativeTab);
        OreFeature.register(name, ore.registry.get());

        return ore;
    }
}
