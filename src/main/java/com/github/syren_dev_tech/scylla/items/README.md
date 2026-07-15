# Items Package

The `items` package contains item registration helpers for generic items, tools, combat equipment, and food.

## What Is Available

- `ModItem`: Generic item registration helper.
- `tools/*`: Tool helper classes (`Pickaxes`, `Axes`, `Shovels`, `Hoes`, `Buckets`, `Shears`, etc.).
- `combat/*`: Combat helper classes (`Swords`, `Bows`, `Crossbows`, `Shields`, `Wearables`, `Arrows`).
- `food/Foods`: Food item registration helper.

## Quick Start

Create a generic item:

```java
import com.github.syren_dev_tech.scylla.items.ModItem;

var sulfurDust = ModItem.create(register, "sulfur_dust", itemsTab);
```

Create a tool item:

```java
import com.github.syren_dev_tech.scylla.items.tools.Pickaxes;
import net.minecraft.world.item.Tiers;

var rubyPickaxe = Pickaxes.create(register, "ruby_pickaxe", new Item.Properties(), Tiers.DIAMOND, combatTab);
```
