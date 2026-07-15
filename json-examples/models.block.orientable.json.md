# models block orientable JSON Example

## File Structure

- Type: resources
- Path: src/main/resources/assets/scylla/models/block/ruby_furnace.json
- Namespace: scylla

## Example JSON

```json
{
  "parent": "minecraft:block/orientable",
  "textures": {
    "top": "scylla:block/ruby_furnace_top",
    "front": "scylla:block/ruby_furnace_front",
    "side": "scylla:block/ruby_furnace_side"
  }
}
```

## What This Defines

An orientable model has a distinct front texture and rotates through blockstate rules.
This is common for machines and utility blocks.
