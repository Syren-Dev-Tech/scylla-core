# Husbandry Package

The `husbandry` package provides reusable egg block helpers and data models for spawnable egg variants.

## What Is Available

- `SmallEggs`, `MediumEggs`, `LargeEggs`, `WaterEggs`: Registration helpers for each egg block family.
- `types/EggData` and typed variants (`SmallEggData`, `MediumEggData`, `LargeEggData`, `WaterEggData`): Configure hatch behavior, sounds, and shape/state options.
- `types/SmallEgg`, `MediumEgg`, `LargeEgg`, `WaterEgg`: Block implementations used by the helpers.

## Quick Start

```java
import com.github.syren_dev_tech.scylla.husbandry.SmallEggs;
import com.github.syren_dev_tech.scylla.husbandry.types.SmallEggData;

var drakeEggData = new SmallEggData<>(() -> ModEntities.DRAKE.get())
    .setMinEggs(1)
    .setMaxEggs(3);

var drakeEgg = SmallEggs.create(register, "drake_egg", drakeEggData, husbandryTab);
```
