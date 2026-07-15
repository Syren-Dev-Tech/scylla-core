# advancements husbandry root JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/advancements/husbandry/scylla_husbandry.json
- Namespace: scylla

## Example JSON

```json
{
  "display": {
    "icon": { "id": "scylla:drake_egg" },
    "title": "Scylla Husbandry",
    "description": "Begin breeding and hatching Scylla creatures.",
    "background": "minecraft:textures/gui/advancements/backgrounds/husbandry.png",
    "frame": "task",
    "show_toast": true,
    "announce_to_chat": true,
    "hidden": false
  },
  "criteria": {
    "obtain_egg": {
      "trigger": "minecraft:inventory_changed",
      "conditions": {
        "items": [
          { "items": "scylla:drake_egg" }
        ]
      }
    }
  },
  "rewards": {
    "experience": 50
  }
}
```

## What This Defines

An advancement with display metadata, one criterion, and XP reward.
Use advancements for progression, tutorials, and unlock signaling.
