# Blocks Package

The `blocks` package contains block registration helpers and grouped APIs for creating vanilla-like block variants.

## What Is Available

- `ModBlocks`: Generic block registration helpers.
- `StoneBlockSet`: Creates a stone set (`base`, `slab`, `stairs`, `wall`, etc.) via `StoneDefinition`.
- `WoodBlockSet`: Creates wood sets and tree-ready sets (`WoodDefinition` / `TreeBlockSetDefinition`).
- `BrickBlockSet`: Creates brick-style block sets.
- `Slabs`, `Stairs`, `WallBlocks`, `FenceBlocks`, `PillarBlocks`, `FallingBlocks`: Focused helpers for common block families.
- Subpackages:
  - `decoration`: furniture, lighting, crafting blocks, signs, crystals, storage, and more.
  - `plants`: flowers, vines, corals, foliage, gourds, melons, saplings, leaves, etc.
  - `redstone`: redstone components such as buttons, doors, repeaters, comparators, pressure plates, lamps.
  - `rails`: rail and powered rail helpers.
  - `fluids`: fluid-adjacent blocks such as ice variants and liquid helpers.
  - `landscapes`, `misc`, `brushable`: terrain and specialty blocks.

## Quick Start

Create a simple block:

```java
import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import net.minecraft.world.level.block.Blocks;

var slate = ModBlocks.create(register, "slate", Blocks.STONE);
```

Create a stone set:

```java
import com.github.syren_dev_tech.scylla.blocks.StoneBlockSet;

var slateSet = StoneBlockSet.create(register, "slate", blocksTab);
```

Create a tree-ready wood set:

```java
import com.github.syren_dev_tech.scylla.blocks.WoodBlockSet;
import com.github.syren_dev_tech.scylla.crops.TreeFeatures;

var maple = WoodBlockSet.createTree(register, "maple", blocksTab);
TreeFeatures.register(register, "maple_tree", maple.treeFeatureBuilder);
```
