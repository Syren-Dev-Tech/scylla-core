# neoforge biome_modifier add_features JSON Example

## File Structure

- Type: data pack (NeoForge)
- Path: src/main/resources/data/scylla/neoforge/biome_modifier/ruby_ore_overworld.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "neoforge:add_features",
  "biomes": "#minecraft:is_overworld",
  "features": "scylla:ruby_ore",
  "step": "underground_ores"
}
```

## What This Defines

A NeoForge biome modifier injects a placed feature into matching biomes.
Use this for mod worldgen integration without replacing full biome JSONs.
