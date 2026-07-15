# Utilities Package

The `utilities` package provides general-purpose helpers used across registration, data handling, config, and math/string workflows.

## What Is Available

- `collections/*`: Generic tuple/bounds helpers and `JsonBuilder`.
- `config/*`: Config schema/group/field helpers (`Config`, `ConfigGroup`, `ConfigField`).
- `files/*`: File and resource-path helpers (`ResourcePath`, platform file helpers).
- `util/*`: Misc helpers (`StringUtil`, `RandUtil`, `Noise`, `Colors`, `ArrayUtil`, `Transform3D`).

## Builder Example: JsonBuilder

```java
import com.github.syren_dev_tech.scylla.utilities.collections.JsonBuilder;

var json = new JsonBuilder();
var root = json.createJsonObject();

root.set("id", "scylla:maple_tree");
root.set("weight", 3);

var tags = root.setArray("tags");
tags.add("forest").add("overworld");

String output = json.stringify(root);
```
