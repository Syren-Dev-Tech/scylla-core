# Creature Definitions

This document is a complete reference for defining, animating, and rendering custom creatures with `CreatureBuilder`.

It covers:

1. End-to-end registration flow
2. Goal-based animation binding with optional animators
3. Weighted animation variation per goal
4. Mask color layers (single, multi-layer, state-driven)
5. Migration notes from older APIs
6. Best practices and troubleshooting
7. Custom NBT schema, selective sync, and NBT-driven tinting

## Quick Start

```java
package com.github.syren_dev_tech.scylla.example;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.CreatureRegistrar;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition;
import com.github.syren_dev_tech.scylla.mobs.ai.LookAtMobGoal;
import com.github.syren_dev_tech.scylla.mobs.ai.SitIdleGoal;
import com.github.syren_dev_tech.scylla.mobs.creatures.AnimationDefinition;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class ExampleWanderer extends CustomCreature {
    public ExampleWanderer(EntityType<? extends PathfinderMob> type, Level level, CreatureBuilder<CustomCreature> builder) {
        super(type, level, builder);
    }
}

public final class CreatureRegistrationExample {
    public static CreatureRegistrar<ExampleWanderer> registerExampleCreature(ModRegister modRegister) {
        CreatureBuilder<ExampleWanderer> builder = new CreatureBuilder<>(
            "example_wanderer",
            modRegister,
            ExampleWanderer::new
        )
            .addAnimator("main", state -> AnimationDefinition.loop("idle"))
            .withAiGoal(
                AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
                    .withAnimator("main", AIGoalDefinition.weightedPool(
                        AIGoalDefinition.w("idle", 6),
                        AIGoalDefinition.w("idle1", 2),
                        AIGoalDefinition.w("idle2", 1)
                    ))
                    .build()
            )
            .withAiGoal(
                AIGoalDefinition.<ExampleWanderer>define(entity -> new LookAtMobGoal(entity))
                    .withAnimator("main", "look", "look_alt")
                    .build()
            );

        CreatureRegistrar<ExampleWanderer> registrar = builder.register();
        registrar.register();
        return registrar;
    }
}
```

## Creature Lifecycle

The normal flow is:

1. Create the builder with `new CreatureBuilder<>(...)`
2. Add animator controllers via `addAnimator(...)`
3. Add AI goals with or without animation bindings
4. Optionally add mask color layers
5. Call `builder.register()`
6. Call `registrar.register()`

## AI Goal Definition Builder

`AIGoalDefinition` unifies goal configuration and optional animation mapping.

Conceptually, each definition contains:

1. Goal factory (`entity -> new SomeGoal(entity)`)
2. Optional animator binding name (`"main"`, `"idle"`, etc.)
3. Animation pool for that goal (weighted or unweighted)

If no animator binding is provided, the goal still runs but does not force animation.

### Basic Goal Without Animator

```java
builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new LookAtMobGoal(entity))
        .build()
);
```

### Goal With Unweighted Animation Pool

```java
builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", "idle", "idle1", "idle2")
        .build()
);
```

Each animation is treated as weight `1`.

### Goal With Weighted Animation Pool

```java
builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main")
        .withAnimation("idle", 10)
        .withAnimation("idle1", 3)
        .withAnimation("idle2", 1)
        .build()
);
```

Approximate selection chance is:

$$
P(animation_i) = \frac{weight_i}{\sum_j weight_j}
$$

So for weights `10, 3, 1`, probabilities are approximately `71.4%`, `21.4%`, and `7.1%`.

### Compact Helper Style

```java
builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", AIGoalDefinition.weightedPool(
            AIGoalDefinition.w("idle", 10),
            AIGoalDefinition.w("idle1", 3),
            AIGoalDefinition.w("idle2", 1)
        ))
        .build()
);

builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", AIGoalDefinition.pool("idle", "idle1", "idle2"))
        .build()
);
```

### Static Import Convenience (Optional)

```java
import static com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition.pool;
import static com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition.w;
import static com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition.weightedPool;

builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", weightedPool(
            w("idle", 10),
            w("idle1", 3),
            w("idle2", 1)
        ))
        .build()
);
```

### Multiple Goal Definitions at Once

```java
List<AIGoalDefinition<ExampleWanderer>> goals = List.of(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", "idle", "idle1", "idle2")
        .build(),
    AIGoalDefinition.<ExampleWanderer>define(entity -> new LookAtMobGoal(entity))
        .withAnimator("main", "look")
        .build()
);

builder.withAiGoalsFromDefinitions(goals);
```

## Animation Selection Behavior

When an animated goal starts:

1. One animation is chosen from that goal's defined pool for each bound animator
2. The chosen animation is forced while the goal remains active
3. On goal stop, forced animation requests from that goal are cleared

Important guarantees:

1. Selection only uses animations provided by that goal definition
2. No fallback animation outside the supplied list is selected
3. A goal without animator binding does not force animation

## Animator Recommendations

1. Keep one semantic animator per channel, for example `main`, `upper_body`, or `face`
2. Use goal pools for variation, not hardcoded random logic in animator handlers
3. Keep baseline controller handler simple, for example default `idle`
4. Use weighted pools for natural frequency control

## Color Mask Layers

`CreatureBuilder` supports optional colorized mask layers. Each layer has:

1. A mask texture supplier
2. A color source (fixed or state-driven)

Render order follows the order in which layers are added.

### Single Fixed-Color Mask Layer

```java
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;

builder.withMaskLayer(
    state -> new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_glow.png"),
    CreatureBuilder.rgba(255, 120, 32, 255)
);
```

### Multi-Layer Mask Example

```java
builder
    .withMaskLayer(
        state -> new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_glow.png"),
        255, 120, 32, 255
    )
    .withMaskLayer(
        state -> new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_markings.png"),
        CreatureBuilder.rgba(30, 180, 255, 210)
    );
```

### State-Driven Mask Color

```java
builder.withMaskLayer(
    state -> new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_glow.png"),
    state -> state.isCurrentAnimation("main", "angry", "roar")
        ? CreatureBuilder.rgba(255, 40, 40, 255)
        : CreatureBuilder.rgba(90, 220, 120, 255)
);
```

### Legacy `withMasks(...)`

`withMasks(...)` remains supported. It now creates a legacy default layer with color `(0, 255, 0, 255)`.

```java
builder.withMasks(state -> new ResourcePath(
    modRegister.modId,
    "textures/entity/example_wanderer/mask_legacy.png"
));
```

### Clearing Mask Layers

```java
builder.clearMaskLayers();
```

This clears all configured mask layers and legacy `withMasks(...)` state.

## Custom NBT Schema and Synced Trait Data

`CreatureBuilder` now supports defining custom NBT fields (including nested dot paths), selecting which fields sync to clients, and using those synced values for render tinting.

### Define Trait Fields in the Builder

```java
builder
    .withNbtField("traits.aggression", "integer")
    .withNbtField("traits.playfullness", "boolean");
```

If you want to sync a field to the client immediately, use the sync overload:

```java
builder
    .withNbtField("traits.aggression", "integer", true)
    .withNbtField("traits.playfullness", "boolean", true);
```

### Define Grouped Fields With Less Boilerplate

```java
builder.withNbtGroupFromStrings(
    "traits",
    Map.of(
        "aggression", "integer",
        "playfullness", "boolean"
    ),
    true
);
```

This creates:

1. `traits.aggression` as `integer`
2. `traits.playfullness` as `boolean`
3. Both fields marked for client sync

### Set and Read Custom Values in Entity Logic

```java
// Server-side updates
creature.setCustomNbt("traits.aggression", 8);
creature.setCustomNbt("traits.playfullness", true);

// Typed reads anywhere you have the creature instance
int aggression = creature.getCustomNbtInt("traits.aggression", 0);
boolean playful = creature.getCustomNbtBoolean("traits.playfullness", false);
```

Typed getters available on `CustomCreature`:

1. `getCustomNbtString(path, defaultValue)`
2. `getCustomNbtInt(path, defaultValue)`
3. `getCustomNbtBoolean(path, defaultValue)`
4. `getCustomNbtFloat(path, defaultValue)`
5. `getCustomNbtDouble(path, defaultValue)`
6. `getCustomNbtLong(path, defaultValue)`
7. `getCustomNbtShort(path, defaultValue)`
8. `getCustomNbtByte(path, defaultValue)`

### NBT-Driven Client Tint Layers for Texture Coloring

Use the new builder helpers to bind synced custom values to mask tint output.

```java
builder
    .addNbtRangeTintLayer(
        new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_aggression.png"),
        "traits.aggression",
        0,
        10,
        CreatureBuilder.rgba(64, 128, 255, 255),
        CreatureBuilder.rgba(255, 64, 64, 255)
    )
    .addNbtBooleanTintLayer(
        new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_playful.png"),
        "traits.playfullness",
        CreatureBuilder.rgba(120, 120, 120, 255),
        CreatureBuilder.rgba(255, 220, 120, 255)
    );
```

Notes:

1. These tint-layer helpers automatically mark the referenced field for client sync.
2. Range tint interpolates from `minColor` to `maxColor` as value moves from `minValue` to `maxValue`.
3. Boolean tint switches directly between the two configured colors.

### Sync Rules

1. Only fields marked as synced are sent through entity synced data.
2. Updating a non-synced custom field does not send sync packets.
3. Synced values are available client-side for renderer logic such as mask coloring.

### Copy/Paste Preset: Traits With Client Tint

Use this as a starting template for a creature that exposes trait data, syncs selected fields, and colors mask overlays on the client.

```java
import java.util.Map;
import com.github.syren_dev_tech.scylla.files.ResourcePath;

CreatureBuilder<ExampleWanderer> builder = new CreatureBuilder<>(
    "example_wanderer",
    modRegister,
    ExampleWanderer::new
)
    .addAnimator("main", state -> AnimationDefinition.loop("idle"))
    .withNbtGroupFromStrings(
        "traits",
        Map.of(
            "aggression", "integer",
            "playfullness", "boolean"
        ),
        true
    )
    .addNbtRangeTintLayer(
        new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_aggression.png"),
        "traits.aggression",
        0,
        10,
        CreatureBuilder.rgba(64, 128, 255, 255),
        CreatureBuilder.rgba(255, 64, 64, 255)
    )
    .addNbtBooleanTintLayer(
        new ResourcePath(modRegister.modId, "textures/entity/example_wanderer/mask_playful.png"),
        "traits.playfullness",
        CreatureBuilder.rgba(120, 120, 120, 255),
        CreatureBuilder.rgba(255, 220, 120, 255)
    );
```

Server-side update example:

```java
// For example, in goal tick/update logic on server side
creature.setCustomNbt("traits.aggression", aggressionScore);
creature.setCustomNbt("traits.playfullness", isPlayful);
```

Client-side read example (for custom render/model logic):

```java
int aggression = creature.getCustomNbtInt("traits.aggression", 0);
boolean playful = creature.getCustomNbtBoolean("traits.playfullness", false);
```

Preset checklist:

1. Keep field paths consistent between schema, updates, and tint layers.
2. Keep range bounds stable (for example, `0..10`) to avoid color jumps.
3. Store server-authored values only; let sync mirror them to the client.

## Animation-Driven Texture and Mask Switching

You can switch both base textures and mask textures from the currently playing animation.

This is useful for creatures like dragons where flight and ground states use different atlases.

### Dragon Example: Different texture and masks while flying

```java
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;

CreatureBuilder<ExampleWanderer> builder = new CreatureBuilder<>(
    "example_dragon",
    modRegister,
    ExampleWanderer::new
)
    .addAnimator("main", state -> AnimationDefinition.loop("idle"))
    .withTextures(state -> {
        if (state.isCurrentAnimation("main", "fly", "glide", "hover")) {
            return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_flying.png");
        }

        return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png");
    })
    .withMaskLayer(
        state -> {
            if (state.isCurrentAnimation("main", "fly", "glide", "hover")) {
                return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_flying_glow.png");
            }

            return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_ground_glow.png");
        },
        CreatureBuilder.rgba(255, 160, 40, 220)
    )
    .withMaskLayer(
        state -> {
            if (state.isCurrentAnimation("main", "fly", "glide", "hover")) {
                return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_flying_markings.png");
            }

            return new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_ground_markings.png");
        },
        state -> state.isCurrentAnimation("main", "fly", "glide", "hover")
            ? CreatureBuilder.rgba(80, 180, 255, 255)
            : CreatureBuilder.rgba(80, 255, 140, 255)
    );
```

### Same dragon example with helper mapping API

```java
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;

ResourcePath groundTexture = new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png");
ResourcePath flyingTexture = new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_flying.png");

ResourcePath groundGlowMask = new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_ground_glow.png");
ResourcePath flyingGlowMask = new ResourcePath(modRegister.modId, "textures/entity/example_dragon/mask_flying_glow.png");

CreatureBuilder<ExampleWanderer> builder = new CreatureBuilder<>(
    "example_dragon",
    modRegister,
    ExampleWanderer::new
)
    .addAnimator("main", state -> AnimationDefinition.loop("idle"))
    .withTextures(
        CreatureBuilder.<ExampleWanderer>animationResources("main", groundTexture)
            .forAnimations(flyingTexture, "fly", "glide", "hover")
            .build()
    )
    .withMaskLayer(
        CreatureBuilder.<ExampleWanderer>animationResources("main", groundGlowMask)
            .forAnimations(flyingGlowMask, "fly", "glide", "hover")
            .build(),
        CreatureBuilder.rgba(255, 160, 40, 220)
    );
```

The same helper works for both `withTextures(...)` and `withMaskLayer(...)` because both consume `Function<CreatureState<T>, ResourcePath>`.

### Texture setter overloads

`CreatureBuilder` now supports both plural and singular texture setter names, plus fixed-path overloads:

```java
builder.withTextures(state -> new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png"));
builder.withTexture(state -> new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png"));

builder.withTextures(new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png"));
builder.withTexture(new ResourcePath(modRegister.modId, "textures/entity/example_dragon/dragon_ground.png"));
```

Use the function overloads when texture depends on animation/state. Use the `ResourcePath` overloads for a fixed texture.

### Why this now works reliably

1. Animator state is reset when an animation stops.
2. Animation controllers are force-reset when changing animation names.
3. `CreatureState.getCurrentAnimation(...)` and `isCurrentAnimation(...)` reflect active playback, so texture/mask suppliers can safely branch on current animation.

## API Cheat Sheet

### CreatureBuilder

1. `withAiGoal(AIGoalDefinition<T>)`
2. `withAiGoalsFromDefinitions(List<AIGoalDefinition<T>>)`
3. `withAiGoal(Function<T, AIGoal>, String animatorName, String... animations)`
4. `withAnimatedAiGoal(...)` and `withAnimatedAiGoals(...)` compatibility overloads
5. `withTextures(Function<CreatureState<T>, ResourcePath>)`, `withTextures(ResourcePath)`
6. `withTexture(Function<CreatureState<T>, ResourcePath>)`, `withTexture(ResourcePath)`
7. `animationResources(String animatorName, ResourcePath fallback)`
8. `withMaskLayer(...)`, `withMaskLayers(...)`, `clearMaskLayers()`
9. `withNbtField(String key, NbtDataType type)`
10. `withNbtField(String key, String type)`
11. `withNbtField(String key, String type, boolean syncToClient)`
12. `withNbtGroup(...)`, `withNbtGroupFromStrings(...)`
13. `withSyncedNbtField(...)`, `withSyncedNbtFields(...)`
14. `addNbtRangeTintLayer(...)`, `addNbtBooleanTintLayer(...)`

### AIGoalDefinition

1. `define(goalFactory)`
2. `withAnimator(animatorName)`
3. `withAnimator(animatorName, String... animations)`
4. `withAnimator(animatorName, List<WeightedAnimation>)`
5. `withAnimation(animation)`
6. `withAnimation(animation, weight)`
7. `pool(...)`, `weightedPool(...)`, and `w(...)`

## Migration Guide

### Older style (still works)

```java
builder.withAiGoal(entity -> new SitIdleGoal(entity), "main", "idle", "idle1", "idle2");
```

### Recommended style

```java
builder.withAiGoal(
    AIGoalDefinition.<ExampleWanderer>define(entity -> new SitIdleGoal(entity))
        .withAnimator("main", "idle", "idle1", "idle2")
        .build()
);
```

Use the definition builder for new code because it is explicit, extensible, and keeps goal behavior bundled in one object.

## Troubleshooting

### Animation Ownership Note

When using `AIGoalDefinition` animation bindings, animation forcing is owned by the `AnimatedGoal` wrapper.

Do not directly call `requestAnimation(...)`, `clearRequestedAnimation(...)`, or `clearAllRequestedAnimations()` inside the underlying goal class (`SitIdleGoal`, `LookAtMobGoal`, or custom goals) for the same animator channel.

Why:

1. The wrapper now manages animator ownership during goal transitions.
2. Direct forcing/clearing inside base goals can override or race with the wrapper.
3. This can cause previous goal animations to persist or newly selected animations to be cleared unexpectedly.

Recommended pattern:

1. Keep the base goal focused on behavior (`canUse`, `canContinueToUse`, `tick`, navigation, targeting).
2. Define animation pools through `AIGoalDefinition` only.
3. Let the wrapper handle start/stop animation claims and clears.

### Goal runs but animation does not change

1. Verify the animator name matches one created with `addAnimator(...)`
2. Verify animation names exist in your model animation data
3. Verify at least one non-empty animation name was supplied

### Animation seems too repetitive

1. Add more variants to the pool
2. Adjust weights for better distribution
3. Split different behaviors into separate goals and pools

### Mask colors do not appear

1. Ensure mask texture path exists and is valid
2. Ensure alpha is non-zero in `rgba(...)`
3. Verify you added mask layers before registration
