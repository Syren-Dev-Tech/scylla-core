# loot_tables archaeology brushable JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/loot_tables/archaeology/suspicious_slate.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:archaeology",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "minecraft:emerald"
        },
        {
          "type": "minecraft:item",
          "name": "scylla:ancient_fragment"
        }
      ]
    }
  ],
  "random_sequence": "scylla:archaeology/suspicious_slate"
}
```

## Optional Block Loot Table (Normal Mining)

Path example:

- src/main/resources/data/scylla/loot_tables/blocks/suspicious_slate.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "scylla:suspicious_slate"
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

The archaeology table controls item rewards from brushing.
The block loot table controls direct drops when the block is broken normally.
