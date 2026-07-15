# blockstates horizontal_facing JSON Example

## File Structure

- Type: resources
- Path: src/main/resources/assets/scylla/blockstates/ruby_furnace.json
- Namespace: scylla

## Example JSON

```json
{
  "variants": {
    "facing=north": { "model": "scylla:block/ruby_furnace" },
    "facing=south": { "model": "scylla:block/ruby_furnace", "y": 180 },
    "facing=west": { "model": "scylla:block/ruby_furnace", "y": 270 },
    "facing=east": { "model": "scylla:block/ruby_furnace", "y": 90 }
  }
}
```

## What This Defines

This maps a `facing` blockstate property to rotated model variants.
Use this for directional blocks such as furnaces, machines, and cabinets.
