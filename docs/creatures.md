# Creature Definitions

This project defines custom creatures through `CreatureBuilder` and then registers the built creature through `CreatureRegistrar`.

The important part for animated AI is that the goal itself now owns the animation behavior. That means you can define a goal once and attach the animations it should drive while it is active.

## Example

```java
package com.github.syren_dev_tech.scylla.example;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.CreatureRegistrar;
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
            .withAiGoal(entity -> new SitIdleGoal(entity), "main", "walk", "idle")
            .withAiGoal(entity -> new LookAtMobGoal(entity), "main", "walk", "idle");

        CreatureRegistrar<ExampleWanderer> registrar = builder.register();
        registrar.register();
        return registrar;
    }
}
```

## What the example does

The `addAnimator("main", ...)` call provides the baseline animation for the creature. While the `SitIdleGoal` or `LookAtMobGoal` is active, the wrapped goal can request `walk` while the creature is moving and `idle` when it is not.

The builder still handles the normal registry flow:

1. Create the creature with `new CreatureBuilder<>(...)`.
2. Add the AI goals and their animation behavior.
3. Call `builder.register()` to create the `CreatureRegistrar`.
4. Call `registrar.register()` to register the renderer.
