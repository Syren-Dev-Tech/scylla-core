# worldgen placed_feature ore JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/worldgen/placed_feature/ruby_ore.json
- Namespace: scylla

## Example JSON

```json
{
  "feature": "scylla:ruby_ore",
  "placement": [
    { "type": "minecraft:count", "count": 10 },
    { "type": "minecraft:in_square" },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:uniform",
        "min_inclusive": { "absolute": -32 },
        "max_inclusive": { "absolute": 64 }
      }
    },
    { "type": "minecraft:biome" }
  ]
}
```

## What This Defines

A placed feature controls how often and where a configured feature spawns.
This is where count, spread, and vertical distribution are defined.
