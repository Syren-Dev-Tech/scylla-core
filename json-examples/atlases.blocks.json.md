# atlases blocks JSON Example

## File Structure

- Type: resources
- Path: src/main/resources/assets/scylla/atlases/blocks.json
- Namespace: scylla

## Example JSON

```json
{
  "sources": [
    {
      "type": "directory",
      "source": "block",
      "prefix": "block/"
    },
    {
      "type": "directory",
      "source": "item",
      "prefix": "item/"
    }
  ]
}
```

## What This Defines

An atlas source declaration for texture stitching.
Use this when custom texture layouts need explicit atlas source control.
