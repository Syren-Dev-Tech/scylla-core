# Creative Tabs Package

The `creative_tabs` package contains helpers for creating and registering creative mode tabs.

## What Is Available

- `CreativeTabs`: Single entry point for creating tabs from an icon item definition.

## Quick Start

```java
import com.github.syren_dev_tech.scylla.creative_tabs.CreativeTabs;

var blocksTab = CreativeTabs.create(register, "scylla_blocks", iconItemDefinition);
```

Use the returned tab key when creating blocks/items in other packages.
