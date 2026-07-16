# Items Package

The `items` package contains item registration helpers for generic items, tools, combat equipment, and food.

## What Is Available

- `ModItem`: Generic item registration helper.
- `Equipment`: Fluent full-set builder for sword + pickaxe + axe + shovel + hoe.
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

Create a full equipment set:

```java
import com.github.syren_dev_tech.scylla.items.Equipment;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

var obsidianEquipment = Equipment
	.set(register, "obsidian")
	.withTier(Tiers.DIAMOND)
	.withSwordProperties(new Item.Properties().stacksTo(1).fireResistant())
	.withPickaxeProperties(new Item.Properties().stacksTo(1))
	.withAxeName("obsidian_battleaxe")
	.register();

var sword = obsidianEquipment.sword();
var pickaxe = obsidianEquipment.pickaxe();
```

## Data Pack References (Tags)

When you add new equipment items, update relevant item tags for compatibility:

- [tags.items.swords.json.md](../../../../../../../../json-examples/tags.items.swords.json.md) - Extend vanilla `minecraft:swords`.
- [tags.items.pickaxes.json.md](../../../../../../../../json-examples/tags.items.pickaxes.json.md) - Extend vanilla `minecraft:pickaxes`.
- [tags.blocks.mineable_pickaxe.json.md](../../../../../../../../json-examples/tags.blocks.mineable_pickaxe.json.md) - Ensure your related blocks are tool-mineable as expected.
