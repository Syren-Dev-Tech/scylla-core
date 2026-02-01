package com.github.syren_dev_tech.scylla.common.mobs.creatures;

import java.util.function.Function;
import com.github.syren_dev_tech.scylla.common.collections.Tuple;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

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
