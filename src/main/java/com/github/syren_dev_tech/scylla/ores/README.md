# Ores Package

The ores package now provides fluent builders for defining full ore materials by category:

- `gem`: ore block + gem item + gem storage block
- `dust`: ore block + dust item + dust storage block
- `metal`: ore block + raw metal + ingot + nugget + raw block + metal block

Gem and metal builders can also register optional tool and armor sets.
Dust intentionally does not expose tool/armor registration.

## Quick Start

### Gem

```java
import com.github.syren_dev_tech.scylla.ores.Ores;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;

var ruby = Ores.gem("ruby")
    .withXpRange(UniformInt.of(3, 7))
    .withToolTier(Tiers.DIAMOND)
    .withArmorMaterial(ArmorMaterials.DIAMOND)
    .register(register);
```

### Dust

```java
import com.github.syren_dev_tech.scylla.ores.Ores;
import net.minecraft.util.valueproviders.ConstantInt;

var sulfur = Ores.dust("sulfur")
    .withDustName("sulfur_dust")
    .withXpRange(ConstantInt.of(1))
    .register(register);
```

### Metal

```java
import com.github.syren_dev_tech.scylla.ores.Ores;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Tiers;

var tin = Ores.metal("tin")
    .withXpRange(UniformInt.of(0, 2))
    .withToolTier(Tiers.IRON)
    .register(register);
```

## Builder Options

### Shared (`gem`, `dust`, `metal`)

- `withOreName(String)`
- `withXpRange(IntProvider)`
- `withOreProperties(BlockBehaviour.Properties)`
- `withItemCreativeTab(ResourceKey<CreativeModeTab>)`
- `withBlockCreativeTab(ResourceKey<CreativeModeTab>)`

### Gem

- `withGemName(String)`
- `withGemProperties(Item.Properties)`
- `withGemBlockName(String)`
- `withGemBlockProperties(BlockBehaviour.Properties)`

### Dust

- `withDustName(String)`
- `withDustProperties(Item.Properties)`
- `withDustBlockName(String)`
- `withDustBlockProperties(BlockBehaviour.Properties)`

### Metal

- `withRawMetalName(String)`
- `withIngotName(String)`
- `withNuggetName(String)`
- `withRawMetalProperties(Item.Properties)`
- `withIngotProperties(Item.Properties)`
- `withNuggetProperties(Item.Properties)`
- `withRawBlockName(String)`
- `withMetalBlockName(String)`
- `withRawBlockProperties(BlockBehaviour.Properties)`
- `withMetalBlockProperties(BlockBehaviour.Properties)`

### Tool and Armor (Gem/Metal only)

- `withToolTier(Tier)` enables tool set registration
- `enableToolSet(boolean)` explicit toggle for tool set
- `withToolProperties(Item.Properties)`
- `withArmorMaterial(Holder<ArmorMaterial>)` enables armor set registration
- `enableArmorSet(boolean)` explicit toggle for armor set
- `withArmorProperties(Item.Properties)`
- `withGearCreativeTab(ResourceKey<CreativeModeTab>)`

Tool set names are generated as:

- `<name>_sword`
- `<name>_pickaxe`
- `<name>_axe`
- `<name>_shovel`
- `<name>_hoe`

Armor set names are generated as:

- `<name>_helmet`
- `<name>_chestplate`
- `<name>_leggings`
- `<name>_boots`

## Worldgen Features for Ores

Ore block registration in `Ores` automatically calls `OreFeature.register(...)`.

Current helper methods:

- `OreFeature.register(name, ore)`
- `OreFeature.register(name, ore, deepslateOre)`
- `OreFeature.register(name, List<TargetBlockState>, TagKey<Biome>)`

### Default Behavior

When you register through `Ores`, the default path:

1. creates a stone-replaceable target list
2. registers a placed feature key (`PlacedOreFeatureRegistry`)
3. associates that key with biome tags (`BiomeOreFeatureRegistry`)

### Custom Target Definitions

If you need custom replacement blocks or biome controls, register features directly:

```java
import java.util.List;
import com.github.syren_dev_tech.scylla.registry.features.FeatureRegistry;
import com.github.syren_dev_tech.scylla.registry.features.OreFeature;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

var customTargets = List.of(
    OreConfiguration.target(FeatureRegistry.getStoneReplaceables(), myOre.registry.get().defaultBlockState()),
    OreConfiguration.target(FeatureRegistry.getDeepslateReplaceables(), myDeepslateOre.registry.get().defaultBlockState())
);

OreFeature.register("my_custom_ore", customTargets, BiomeTags.IS_OVERWORLD);
```

### Bootstrap/DataGen Notes

The configured/placed feature bootstrap methods in `FeatureRegistry` and `PlacedFeatureRegistry` are scaffolded with commented examples. Use the generated ore metadata maps as your source of truth when wiring your data bootstrap pipeline.

In short:

- register ore blocks with `Ores.*Builder.register(...)`
- use `OreFeature.register(...)` overloads for custom targets/biome tags
- consume registry maps in your configured + placed feature bootstrap during datagen/runtime setup
