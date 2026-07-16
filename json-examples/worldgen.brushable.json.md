# worldgen brushable JSON Example

## File Structure

- Type: data pack
- Configured feature path: src/main/resources/data/scylla/worldgen/configured_feature/suspicious_slate_patch.json
- Placed feature path: src/main/resources/data/scylla/worldgen/placed_feature/suspicious_slate_patch.json
- Biome modifier path: src/main/resources/data/scylla/neoforge/biome_modifier/suspicious_slate_patch.json
- Namespace: scylla

## Configured Feature JSON

```json
{
  "type": "minecraft:ore",
  "config": {
    "size": 6,
    "discard_chance_on_air_exposure": 0.0,
    "targets": [
      {
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:stone_ore_replaceables"
        },
        "state": {
          "Name": "scylla:suspicious_slate"
        }
      }
    ]
  }
}
```

## Placed Feature JSON

```json
{
  "feature": "scylla:suspicious_slate_patch",
  "placement": [
    { "type": "minecraft:count", "count": 4 },
    { "type": "minecraft:in_square" },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:uniform",
        "min_inclusive": { "absolute": -16 },
        "max_inclusive": { "absolute": 48 }
      }
    },
    { "type": "minecraft:biome" }
  ]
}
```

## NeoForge Biome Modifier JSON

```json
{
  "type": "neoforge:add_features",
  "biomes": "#minecraft:is_overworld",
  "features": "scylla:suspicious_slate_patch",
  "step": "underground_ores"
}
```

## Important For Brush Rewards

Generated brushable blocks need a loot table key on their `BrushableBlockEntity`.
Placing the block state alone is not enough for archaeology drops.

When generating these blocks through features, set the block entity loot table after placement in code:

```java
var pos = placedPos;
world.setBlock(pos, suspiciousSlate.defaultBlockState(), 2);

var blockEntity = world.getBlockEntity(pos);
if (blockEntity instanceof net.minecraft.world.level.block.entity.BrushableBlockEntity brushable) {
    brushable.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("scylla", "archaeology/suspicious_slate")), world.getSeed() ^ pos.asLong());
}
```

Without that step, brushing still replaces the block, but item rewards will be empty.
