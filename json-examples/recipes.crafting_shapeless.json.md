# recipes crafting_shapeless JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/recipes/ruby_from_block.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    { "item": "scylla:ruby_block" }
  ],
  "result": {
    "id": "scylla:ruby",
    "count": 9
  }
}
```

## What This Defines

A shapeless recipe where input order and slot position do not matter.
Use this for unpacking and simple transformations.
