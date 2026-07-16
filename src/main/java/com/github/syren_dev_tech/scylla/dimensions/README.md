# Dimensions Package

The `dimensions` package provides helpers for custom dimension key definitions and custom portal structures.

## What Is Available

- `Dimensions`: Builder API for creating custom dimension keys (`Level`, `DimensionType`, `LevelStem`).
- `PortalSet`: Builder API for defining a portal frame, optional key item, and vertical/horizontal portal blocks.
- `Portals`: Registration helpers for horizontal and vertical portal blocks with configurable transit rules.
- `portals/HorizontalPortal`: End-portal-like horizontal portal block behavior.
- `portals/VerticalPortal`: Vertical portal implementation.
- `portals/HorizontalPortalFrame`: Frame block used by horizontal portals.
- `portals/PortalFrameBlock`: Generic frame block with optional key-item insertion support.
- `portals/PortalTransit`: Transit strategy interface to route any source dimension to any destination dimension.
- `PortalShapeMode`: Shape selector for vertical, horizontal, or dual-orientation portal sets.

## Quick Start

Create custom dimension keys:

```java
import com.github.syren_dev_tech.scylla.dimensions.Dimensions;

var moon = Dimensions.create(register, "moon")
    .withDimensionTypeName("moon_type")
    .build();

// moon.levelKey()
// moon.typeKey()
// moon.levelStemKey()
```

Register a custom horizontal portal that always routes to the moon:

```java
import com.github.syren_dev_tech.scylla.dimensions.Portals;

var moonPortal = Portals.createHorizontal(register, "moon_portal", moon.levelKey());
```

Define a complete portal set with a frame, key item, and both portal orientations:

```java
import com.github.syren_dev_tech.scylla.dimensions.PortalSet;
import com.github.syren_dev_tech.scylla.dimensions.PortalShapeMode;
import com.github.syren_dev_tech.scylla.dimensions.portals.PortalTransit;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

var portal = PortalSet.create(register, "moon_gate", PortalTransit.between(Level.OVERWORLD, moon.levelKey()))
    .withShapeMode(PortalShapeMode.BOTH)
    .withFrameSourceBlock(net.minecraft.world.level.block.Blocks.OBSIDIAN)
    .withKeyItem(Items.ENDER_EYE)
    .register();
```

Use the low-level transit helper when you want direct block registration:

```java
import java.util.Map;
import com.github.syren_dev_tech.scylla.dimensions.Portals;
import com.github.syren_dev_tech.scylla.dimensions.portals.PortalTransit;

var transit = PortalTransit.map(Map.of(
    Level.OVERWORLD, moon.levelKey(),
    moon.levelKey(), Level.OVERWORLD,
    Level.NETHER, Level.END
));

var unstableGate = Portals.createVertical(register, "unstable_gate", transit, net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.NETHER_PORTAL));
```
