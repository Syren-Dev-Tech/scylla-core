package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.scylla.mobs.AnimationHandler;
import com.github.syren_dev_tech.scylla.utilities.collections.Tuple;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class Animator<T extends CustomCreature> {
    private final String name;
    private final AnimationHandler<T> handler;
    private String currentAnimation = "";

    public Animator(String name, AnimationHandler<T> handler) {
        this.name = name;
        this.handler = handler;
    }

    public PlayState apply(AnimationState<T> event) {
        T entity = event.getAnimatable();

        // First check for any forced animation requests from AI/state
        var forced = entity.getState().getForcedAnimation(this.name);
        Tuple<String, Boolean> result = null;
        if (forced != null) {
            result = forced;
        } else {
            result = this.handler.apply(event, entity.getState());
        }

        if (result == null || result.x == null || result.x.isEmpty() || Boolean.FALSE.equals(result.y))
            return PlayState.STOP;

        this.currentAnimation = result.x;

        return event.setAndContinue(RawAnimation.begin().thenLoop(result.x));
    }

    public Tuple<String, Boolean> getAnimationForState(CreatureState<T> state) {
        return this.handler.apply(null, state);
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }
}
