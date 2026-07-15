# recipes crafting_shaped JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/recipes/ruby_block.json
- Namespace: scylla

## Example JSON

```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "RRR",
    "RRR",
    "RRR"
  ],
  "key": {
    "R": {
      "item": "scylla:ruby"
    }
  },
  "result": {
    "id": "scylla:ruby_block",
    "count": 1
  }
}
```

## What This Defines

A pattern-based crafting recipe.
Use this when slot arrangement matters.
