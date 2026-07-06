package com.github.syren_dev_tech.scylla.mobs;

import com.github.syren_dev_tech.scylla.mobs.creatures.AnimationContext;
import com.github.syren_dev_tech.scylla.mobs.creatures.AnimationDefinition;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import software.bernie.geckolib.animation.AnimationState;

/**
 * Functional handler used by the builder to determine which animation to play. Gives access to both the Geckolib {@link AnimationState} and the {@link CreatureState} so animation decisions can be based on AI state and animation-specific information (ticks, partials, etc.).
 */
@FunctionalInterface
public interface AnimationHandler<T extends com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature> {
    AnimationDefinition apply(AnimationState<T> animationState, CreatureState<T> state);

    static <T extends com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature> AnimationHandler<T> fromStateHandler(java.util.function.Function<CreatureState<T>, AnimationDefinition> fn) {
        return (animationState, state) -> fn.apply(state);
    }

    default AnimationDefinition apply(AnimationContext<T> ctx) {
        return apply(ctx.getAnimationState(), ctx.getCreatureState());
    }
}
