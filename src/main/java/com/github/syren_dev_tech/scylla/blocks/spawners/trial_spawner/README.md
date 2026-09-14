# Trial Spawner Configs

The custom trial spawner supports JSON-driven definitions under the data pack folder:

`data/<modid>/trial_spawner_configs/<config_name>.json`

## Example

```json
{
  "total_waves": 3,
  "trigger_type": "PROXIMITY",
  "loot_table": "scylla:blocks/trial_loot",
  "spawns": [
    {
      "entity": "minecraft:zombie",
      "count": 5
    },
    {
      "entity": "minecraft:creeper",
      "count": 2
    }
  ]
}
```

## Fields

- `total_waves`: Total number of waves the spawner will dispatch.
- `trigger_type`: One of `PROXIMITY`, `REDSTONE`, or `INTERACTION`.
- `loot_table`: The loot table used when the spawner resolves rewards or drops.
- `spawns`: Ordered list of spawn entries. Each entry contains:
  - `entity`: A registry id such as `minecraft:zombie`.
  - `count`: Number of mobs to spawn for that entry.

## Trigger Types

- `PROXIMITY`: Triggers when a player is nearby.
- `REDSTONE`: Triggers when the block receives a redstone signal.
- `INTERACTION`: Intended for interaction-based activation on block use.

## Runtime Notes

- The block entity loads the config by id and resolves entries from the spawner’s configured registry map.
- The example config is intentionally data-driven so you can tune enemy combinations and wave counts without recompiling Java.
- Reuse the same structure to add new challenge rooms, event encounters, or boss-style waves.

## Implementation

The config model is defined in `TrialSpawnerConfig`, and the runtime block logic lives in `TrialSpawnerBlockEntity` and `CustomTrialSpawnerBlock`.