# worldgen configured_feature ore JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/worldgen/configured_feature/ruby_ore.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:ore",
  "config": {
    "size": 8,
    "discard_chance_on_air_exposure": 0.0,
    "targets": [
      {
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:stone_ore_replaceables"
        },
        "state": {
          "Name": "scylla:ruby_ore"
        }
      }
    ]
  }
}
```

## What This Defines

A configured ore feature defines what to place and what it can replace.
Pair this with a placed feature for frequency and height rules.
