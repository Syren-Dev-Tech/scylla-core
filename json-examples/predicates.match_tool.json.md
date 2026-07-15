# predicates match_tool JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/predicate/has_silk_touch.json
- Namespace: scylla

## Example JSON

```json
{
  "condition": "minecraft:match_tool",
  "predicate": {
    "enchantments": [
      {
        "enchantment": "minecraft:silk_touch",
        "levels": {
          "min": 1
        }
      }
    ]
  }
}
```

## What This Defines

A reusable predicate that checks the tool used in a loot or trigger context.
Useful for keeping loot table conditions modular and consistent.
