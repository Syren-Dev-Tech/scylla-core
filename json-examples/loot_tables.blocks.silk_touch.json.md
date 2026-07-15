# loot_tables blocks silk_touch JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/loot_tables/blocks/ruby_ore.json
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
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "name": "scylla:ruby_ore",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "enchantments": [
                      {
                        "enchantment": "minecraft:silk_touch",
                        "levels": { "min": 1 }
                      }
                    ]
                  }
                }
              ]
            },
            {
              "type": "minecraft:item",
              "name": "scylla:ruby"
            }
          ]
        }
      ]
    }
  ]
}
```

## What This Defines

This ore drops itself with Silk Touch, otherwise it drops a raw item.
Use this pattern for ore and special fragile blocks.
