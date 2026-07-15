# loot_tables blocks self_drop JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/loot_tables/blocks/basalt_bricks.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "scylla:basalt_bricks"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    }
  ]
}
```

## What This Defines

This block loot table drops the block itself when broken.
This is the default behavior for many solid blocks.
