package com.github.syren_dev_tech.scylla.common.mobs.creatures;

import java.util.function.Function;
import com.github.syren_dev_tech.scylla.utilities.collections.Tuple;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class Animator<T extends CustomCreature> {
    private final Function<CreatureState<T>, Tuple<String, Boolean>> handler;
    private String currentAnimation = "";

    public Animator(Function<CreatureState<T>, Tuple<String, Boolean>> handler) {
        this.handler = handler;
    }

    public PlayState apply(AnimationState<T> event) {
        T entity = event.getAnimatable();

        Tuple<String, Boolean> result = this.handler.apply(entity.getState());
        if (result == null || result.x.isEmpty() || Boolean.FALSE.equals(result.y))
            return PlayState.STOP;

        this.currentAnimation = result.x;

        return event.setAndContinue(RawAnimation.begin().thenLoop(result.x));
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }
}
