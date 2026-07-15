# loot_tables chests simple JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/loot_tables/chests/ruins_cache.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:chest",
  "pools": [
    {
      "rolls": { "min": 2, "max": 5 },
      "entries": [
        {
          "type": "minecraft:item",
          "name": "scylla:ruby",
          "weight": 6
        },
        {
          "type": "minecraft:item",
          "name": "minecraft:gold_ingot",
          "weight": 12
        },
        {
          "type": "minecraft:item",
          "name": "scylla:ruby_sword",
          "weight": 1
        }
      ]
    }
  ]
}
```

## What This Defines

A chest loot table with weighted random entries and random roll count.
This is used by structures, archaeology, and custom generated containers.
