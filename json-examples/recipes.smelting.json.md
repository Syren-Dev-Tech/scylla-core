# recipes smelting JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/recipes/ruby_from_smelting_ruby_ore.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:smelting",
  "ingredient": {
    "item": "scylla:ruby_ore"
  },
  "result": {
    "id": "scylla:ruby"
  },
  "experience": 0.7,
  "cookingtime": 200
}
```

## What This Defines

A furnace-style cooking recipe.
Use this for ore refining, food cooking, or material conversion.
