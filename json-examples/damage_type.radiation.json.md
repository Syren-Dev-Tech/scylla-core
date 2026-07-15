# damage_type radiation JSON Example

## File Structure

- Type: data pack
- Path: src/main/resources/data/scylla/damage_type/radiation.json
- Namespace: scylla

## Example JSON

```json
{
  "message_id": "radiation",
  "scaling": "always",
  "exhaustion": 0.0,
  "effects": "drowning",
  "death_message_type": "default"
}
```

## What This Defines

A custom damage type definition used by damage sources in code.
This controls death messages, scaling behavior, and effect classification.
