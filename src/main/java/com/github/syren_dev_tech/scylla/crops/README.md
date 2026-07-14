# Crops Package

The crops package provides helpers for plant-related block registration and lightweight tree worldgen definitions.

## Package Contents

- `Crops`: Registers custom crop blocks based on `CropProperties` and a seed item.
- `Farmland`: Registers farmland-like blocks.
- `Haybales`: Registers hay bale blocks.
- `Stems`: Registers stem and attached stem pairs (melon/pumpkin style).
- `TreeFeatureBuilder`: Fluent builder for tree configured feature settings and placement metadata.
- `TreeFeatures`: Registry store for tree feature definitions and generated feature keys.
- `types/*`: Crop block types and supporting properties/items.

## Quick Start

```java
import com.github.syren_dev_tech.scylla.crops.Crops;
import com.github.syren_dev_tech.scylla.crops.types.CropProperties;

var blueberryCrop = Crops.create(register, "blueberry_crop", new CropProperties(), blueberrySeeds);
```

## API Reference

### 1) Crops

Registers a crop block:

```java
var crop = Crops.create(register, "rice_crop", new CropProperties(), riceSeeds);
```

`CropProperties` supports:

- custom base block properties
- custom soil block
- max age
- shape by age

Example with custom soil:

```java
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

var cropProps = new CropProperties(
    Properties.ofFullCopy(Blocks.WHEAT),
    BlockDefinition.of(() -> customFarmland.registry.get())
);

var riceCrop = Crops.create(register, "rice_crop", cropProps, riceSeeds);
```

### 2) Farmland

Registers a farm block with optional creative tab and properties.

```java
var ashFarmland = Farmland.create(register, "ash_farmland", cropsTab);
```

```java
var fertileFarmland = Farmland.create(
    register,
    "fertile_farmland",
    Properties.ofFullCopy(Blocks.FARMLAND)
);
```

### 3) Haybales

Registers a hay block variant.

```java
var ryeHay = Haybales.create(register, "rye_hay_bale", cropsTab);
```

### 4) Stems

Creates both growing and attached stems and returns a `StemDefinition` with both block definitions plus generated keys.

```java
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

ResourceKey<Block> fruitKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, "wild_gourd").get());
ResourceKey<Item> seedKey = ResourceKey.create(Registries.ITEM, new ResourcePath(register.modId, "wild_gourd_seeds").get());

var stems = Stems.create(register, "wild_gourd_stem", fruitKey, seedKey);
```

### 5) TreeFeatureBuilder

Builds tree worldgen settings from blocks plus placement metadata.

Default builder uses oak-like settings and overworld biome tag.

```java
var treeBuilder = TreeFeatureBuilder.create()
    .withWood(myWoodLog)
    .withLeaves(myLeaves)
    .withBiomeTag(BiomeTags.IS_FOREST)
    .withPlacementModifiers(List.of(
        CountPlacement.of(3),
        InSquarePlacement.spread(),
        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.top()),
        BiomeFilter.biome()
    ));
```

### 6) TreeFeatures

Registers a built tree definition into the in-memory tree feature map and creates:

- configured feature key (`name`)
- placed feature key (`name + "_placed"`)

```java
var treeFeature = TreeFeatures.register(register, "silver_birch_tree", treeBuilder);
```

Or directly from wood/leaves:

```java
var treeFeature = TreeFeatures.register(register, "silver_birch_tree", myWoodLog, myLeaves);
```

Note: `TreeFeatures` currently stores metadata and generated keys. Hooking into actual bootstrap/datagen pipelines should consume `TreeFeatures.getFeatures()`.

## Tree Set Integration (WoodBlockSet)

`WoodBlockSet` now includes tree-focused creators that:

1. build all wood set blocks (`WoodDefinition`)
2. create a matching leaves block (`name + "_leaves"`)
3. apply `wood.logs.log` and the created leaves to a `TreeFeatureBuilder`

Example:

```java
import com.github.syren_dev_tech.scylla.blocks.WoodBlockSet;
import com.github.syren_dev_tech.scylla.crops.TreeFeatures;

var mapleTreeSet = WoodBlockSet.createTree(register, "maple", treesTab);
var mapleTreeFeature = TreeFeatures.register(register, "maple_tree", mapleTreeSet.treeFeatureBuilder);
```

Customized source blocks:

```java
var cedarTreeSet = WoodBlockSet.createTree(
    register,
    "cedar",
    Blocks.SPRUCE_LOG,
    Blocks.SPRUCE_PLANKS,
    Blocks.SPRUCE_LEAVES,
    treesTab
);

TreeFeatures.register(register, "cedar_tree", cedarTreeSet.treeFeatureBuilder);
```

## End-to-End Example

```java
// 1) Register seed item elsewhere (riceSeeds)

// 2) Register crop + farmland
var riceFarmland = Farmland.create(register, "rice_farmland", cropsTab);
var riceCrop = Crops.create(register, "rice_crop", new CropProperties(), riceSeeds);

// 3) Register wood + leaves + tree builder as one set
var willowTreeSet = WoodBlockSet.createTree(register, "willow", treesTab);

// 4) Register tree feature metadata/keys
var willowTree = TreeFeatures.register(register, "willow_tree", willowTreeSet.treeFeatureBuilder);
```

## Notes

- This package is helper-first: it focuses on registration objects and reusable definitions.
- For tree worldgen, this package provides definition and key generation; world placement/bootstrap wiring should consume the generated registrations.
