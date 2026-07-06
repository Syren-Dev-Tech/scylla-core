package com.github.syren_dev_tech.scylla.mobs.ai;

import java.util.HashMap;
import java.util.Map;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.utilities.collections.Tuple;
import net.minecraft.world.entity.Mob;

public class AnimatedGoal<T extends CustomCreature> extends AIGoal {

    private final AIGoal delegate;
    private final Map<String, Tuple<String, String>> animations = new HashMap<>();

    public AnimatedGoal(Mob self, AIGoal delegate) {
        super(self);
        this.delegate = delegate;
    }

    public AnimatedGoal<T> withAnimation(String animatorName, String movingAnimation, String idleAnimation) {
        if (animatorName == null || animatorName.isEmpty()) {
            return this;
        }

        this.animations.put(animatorName, new Tuple<>(movingAnimation, idleAnimation));
        return this;
    }

    @Override
    public boolean canUse() {
        return this.delegate.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.delegate.canContinueToUse();
    }

    @Override
    public void start() {
        this.delegate.start();
        this.applyAnimations();
    }

    @Override
    public void stop() {
        this.delegate.stop();
        this.clearAnimations();
    }

    @Override
    public void tick() {
        this.delegate.tick();
        this.applyAnimations();
    }

    @Override
    public int getWeight() {
        return this.delegate.getWeight();
    }

    private void applyAnimations() {
        if (!(this.self instanceof CustomCreature creature)) {
            return;
        }

        boolean moving = this.self.getDeltaMovement().horizontalDistanceSqr() > 0.0001D;

        this.animations.forEach((animatorName, animationPair) -> {
            String animation = moving ? animationPair.x : animationPair.y;

            if (animation == null || animation.isEmpty()) {
                creature.clearRequestedAnimation(animatorName);
                return;
            }

            creature.requestAnimation(animatorName, animation, true);
        });
    }

    private void clearAnimations() {
        if (!(this.self instanceof CustomCreature creature)) {
            return;
        }

        this.animations.keySet().forEach(creature::clearRequestedAnimation);
    }
}
