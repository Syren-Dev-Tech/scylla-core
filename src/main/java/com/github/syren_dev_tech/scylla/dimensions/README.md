# Dimensions Package

The `dimensions` package currently focuses on custom portal block implementations.

## What Is Available

- `portals/HorizontalPortal`: End-portal-like horizontal portal block behavior.
- `portals/VerticalPortal`: Vertical portal implementation.
- `portals/HorizontalPortalFrame`: Frame block used by horizontal portals.

## Quick Start

Register a horizontal portal block:

```java
import com.github.syren_dev_tech.scylla.dimensions.portals.HorizontalPortal;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

var moonPortal = register.blockRegistry.register(
    "moon_portal",
    BlockDefinition.of(() -> new HorizontalPortal(Properties.ofFullCopy(Blocks.END_PORTAL), Level.END))
);
```

Portal destination behavior is implemented by the portal block classes themselves.
