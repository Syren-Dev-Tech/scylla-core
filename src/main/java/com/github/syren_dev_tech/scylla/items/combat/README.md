# Combat Items Package

The combat package contains helpers for weapons, bows, shields, arrows, and wearable armour pieces.

## Wearables Builder

Use `Wearables.set(...)` to register a full armour set in one flow.

```java
import com.github.syren_dev_tech.scylla.items.combat.Wearables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;

var obsidianSet = Wearables
    .set(register, "obsidian")
    .withArmorMaterial(ArmorMaterials.NETHERITE)
    .withProperties(new Item.Properties().stacksTo(1))
    .withPieceName(ArmorItem.Type.HELMET, "obsidian_visor")
    .withGeckoModel(
        ArmorItem.Type.HELMET,
        ResourceLocation.fromNamespaceAndPath("examplemod", "geo/armor/obsidian_visor.geo.json"),
        ResourceLocation.fromNamespaceAndPath("examplemod", "textures/models/armor/obsidian_visor.png"),
        ResourceLocation.fromNamespaceAndPath("examplemod", "animations/armor/obsidian_visor.animation.json")
    )
    .register();

// Access the registered pieces
var helmet = obsidianSet.helmet();
var chestplate = obsidianSet.chestplate();
var leggings = obsidianSet.leggings();
var boots = obsidianSet.boots();
```

## Custom Model Behavior

- If a piece has model config (`withGeckoModel` / `withModelConfig`), `Wearables` registers it as `ArmourPiece`.
- `ArmourPiece` exposes `shouldRenderCustomModelWhenWorn()` and `modelConfig()` so your client render setup can decide when to use a GeckoLib armour renderer.
- Pieces without model config stay as vanilla `ArmorItem` registrations.

## Typical Client Integration

The GeckoLib provider boilerplate is centralized in `combat/client/WearableArmourRenderers`.

- `ArmourPiece` now delegates `createGeoRenderer(...)` to that helper.
- Any piece registered with `withGeckoModel(...)` automatically gets a config-driven `GeoArmorRenderer` when worn.
- If model config is not enabled, rendering falls back to the original vanilla armor model.
