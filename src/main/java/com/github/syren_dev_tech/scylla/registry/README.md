# Registry Package

The `registry` package provides the registration backbone used by all content helper packages.

## What Is Available

- `ModRegistrars`: Container for platform-specific registrar implementations.
- `ModRegister`: Unified registration context (`blockRegistry`, `itemRegistry`, `creativeTabRegistry`, `mobRegistry`, config map).
- `BlockRegistry`, `ItemRegistry`, `CreativeTabRegistry`, `MobRegistry`: Concrete typed registry wrappers.
- `definitions/*`: Return types/wrappers for registered content (`BlockDefinition`, `ItemDefinition`, `WoodDefinition`, etc.).
- `features/*`: Feature metadata helpers for worldgen (including ore feature key and biome mappings).

## Quick Start

```java
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.ModRegistrars;

var registrars = new ModRegistrars()
    .setBlockRegistrar(blockRegistrar)
    .setItemRegistrar(itemRegistrar)
    .setCreativeTabRegistrar(tabRegistrar)
    .setEntityRegistrar(entityRegistrar);

var register = new ModRegister("scylla", registrars);
```

Most package APIs accept this `ModRegister` instance as their first argument.
