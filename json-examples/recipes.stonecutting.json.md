# recipes stonecutting JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/recipes/basalt_brick_slab_from_stonecutting.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:stonecutting",
  "ingredient": {
    "item": "scylla:basalt_bricks"
  },
  "result": {
    "id": "scylla:basalt_brick_slab",
    "count": 2
  }
}
```

## What This Defines

A stonecutter conversion recipe.
Use this for efficient block variant conversion such as slab, stair, and wall outputs.
