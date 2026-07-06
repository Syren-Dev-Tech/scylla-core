package com.github.syren_dev_tech.scylla.mobs;

import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.AnimationContext;
import com.github.syren_dev_tech.scylla.utilities.collections.Tuple;
import software.bernie.geckolib.animation.AnimationState;

/**
 * Functional handler used by the builder to determine which animation to play. Gives access to both the Geckolib {@link AnimationState} and the {@link CreatureState} so animation decisions can be based on AI state and animation-specific information (ticks, partials, etc.).
 */
@FunctionalInterface
public interface AnimationHandler<T extends com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature> {
    Tuple<String, Boolean> apply(AnimationState<T> animationState, CreatureState<T> state);

    static <T extends com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature> AnimationHandler<T> fromStateHandler(java.util.function.Function<CreatureState<T>, Tuple<String, Boolean>> fn) {
        return (animationState, state) -> fn.apply(state);
    }

    default Tuple<String, Boolean> apply(AnimationContext<T> ctx) {
        return apply(ctx.getAnimationState(), ctx.getCreatureState());
    }
}
