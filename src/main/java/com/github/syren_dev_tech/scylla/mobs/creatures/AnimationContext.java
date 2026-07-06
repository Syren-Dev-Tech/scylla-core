package com.github.syren_dev_tech.scylla.mobs.creatures;

import software.bernie.geckolib.animation.AnimationState;
import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;

/**
 * Lightweight wrapper that pairs Geckolib's {@link AnimationState} with our {@link CreatureState} so animation handlers can make decisions with both animation-specific and AI/creature-specific context.
 */
public final class AnimationContext<T extends CustomCreature> {
    private final AnimationState<T> animationState;
    private final CreatureState<T> creatureState;
    private final CreatureBuilder<T> builder;

    public AnimationContext(AnimationState<T> animationState, CreatureState<T> creatureState) {
        this.animationState = animationState;
        this.creatureState = creatureState;
        this.builder = creatureState.getBuilder();
    }

    public AnimationState<T> getAnimationState() {
        return animationState;
    }

    public CreatureState<T> getCreatureState() {
        return creatureState;
    }

    public CreatureBuilder<T> getBuilder() {
        return builder;
    }
}
