# sounds JSON Example

## File Structure

- Type: resources
- Path: src/main/resources/assets/scylla/sounds.json
- Namespace: scylla

## Example JSON

```json
{
  "entity.drake.ambient": {
    "subtitle": "subtitles.scylla.entity.drake.ambient",
    "sounds": [
      "scylla:entity/drake/ambient1",
      {
        "name": "scylla:entity/drake/ambient2",
        "volume": 0.8,
        "pitch": 1.1
      }
    ]
  },
  "block.ruby_furnace.fire": {
    "sounds": [
      "scylla:block/ruby_furnace_fire"
    ]
  }
}
```

## What This Defines

Registers sound events and maps them to one or more sound files.
This is required for custom sound playback from blocks, entities, and items.
