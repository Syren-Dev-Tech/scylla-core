# Brushable Blocks

The `brushable` package provides helpers to register custom brushable blocks.

## What Is Available

- `Brushables`: Unified builder for brushable registration plus required data/worldgen file paths and JSON templates.
- `BrushableSands`: Registers falling brushable blocks (suspicious sand/gravel style).
- `BrushableStones`: Registers non-falling brushable blocks.
- `types/CustomBrushableBlock`: Brushable base block that resolves replacement through vanilla brush logic while staying non-falling.
- `types/CustomFallingBrushableBlock`: Falling variant for suspicious-sand behavior.

## Replacement Logic

When brushing completes, Minecraft replaces the block with the configured `turns_into` block.
Your block definition controls this through the `dustedBlock` argument:

```java
import com.github.syren_dev_tech.scylla.blocks.ModBlocks;
import com.github.syren_dev_tech.scylla.blocks.brushable.BrushableSands;

var weatheredSlate = ModBlocks.create(register, "weathered_slate");
var suspiciousSlate = BrushableSands.create(register, "suspicious_slate", weatheredSlate);
```

In this example, brushing `suspicious_slate` replaces it with `weathered_slate`.

Use the unified builder when you want one definition that includes registration inputs and data file requirements:

```java
import com.github.syren_dev_tech.scylla.blocks.brushable.Brushables;
import net.minecraft.sounds.SoundEvents;

var brushable = Brushables.sand(register, "suspicious_slate")
	.turnsInto(weatheredSlate)
	.withSounds(SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED)
	.register();

var requiredFiles = brushable.dataRequirements();
String archaeologyLootJson = requiredFiles.archaeologyLootTableTemplate();
String worldgenConfiguredJson = requiredFiles.configuredFeatureTemplate("scylla:suspicious_slate");
```

`requiredFiles` also contains expected paths for:

- archaeology loot table
- block loot table
- configured feature
- placed feature
- biome modifier

## Loot and Worldgen

Use both of these loot table types when shipping brushable content:

1. Archaeology loot table for brushed rewards.
2. Block loot table for normal block-breaking behavior.

See examples in:

- `json-examples/loot_tables.archaeology.brushable.json.md`
- `json-examples/worldgen.brushable.json.md`
