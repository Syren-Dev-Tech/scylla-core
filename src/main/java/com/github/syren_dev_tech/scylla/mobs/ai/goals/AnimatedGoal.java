package com.github.syren_dev_tech.scylla.mobs.ai.goals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoalDefinition.WeightedAnimation;
import net.minecraft.world.entity.Mob;

public class AnimatedGoal<T extends CustomCreature> extends AIGoal {

    private final AIGoal delegate;
    private final Map<String, List<WeightedAnimation>> animations = new HashMap<>();
    private final Map<String, MovementAnimationSet> movementAnimations = new HashMap<>();
    private final Map<String, String> selectedAnimations = new HashMap<>();

    public AnimatedGoal(Mob self, AIGoal delegate) {
        super(self);
        this.delegate = delegate;
        this.delegate.useAsDeligate();
        this.setFlags(delegate.getFlags());
    }

    public AnimatedGoal<T> withAnimation(String animatorName, String... possibleAnimations) {
        List<WeightedAnimation> validAnimations = new ArrayList<>();

        if (possibleAnimations != null) {
            for (String animation : possibleAnimations) {
                if (animation != null && !animation.isEmpty()) {
                    validAnimations.add(WeightedAnimation.of(animation));
                }
            }
        }

        return this.setAnimationOptions(animatorName, validAnimations);
    }

    public AnimatedGoal<T> withAnimation(String animatorName, List<WeightedAnimation> possibleAnimations) {
        return this.setAnimationOptions(animatorName, possibleAnimations);
    }

    public AnimatedGoal<T> withAnimation(String animatorName, String movingAnimation, String idleAnimation) {
        if (animatorName == null || animatorName.isEmpty()) {
            return this;
        }

        this.animations.remove(animatorName);
        this.selectedAnimations.remove(animatorName);
        this.movementAnimations.put(animatorName, new MovementAnimationSet(normalizeAnimationName(movingAnimation), normalizeAnimationName(idleAnimation)));
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
        ScyllaCommon.LOGGER.debug("Entity {} changing to goal {}", this.getSelf().getId(), this.delegate.getClass().getSimpleName());
        this.delegate.start();
        this.claimAnimations();
        this.chooseAnimations();
        this.applyAnimations();
    }

    @Override
    public void stop() {
        ScyllaCommon.LOGGER.debug("Entity {} goal {} stopped", this.getSelf().getId(), this.delegate.getClass().getSimpleName());
        this.delegate.stop();
        this.clearAnimations();
        this.selectedAnimations.clear();
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
        if (!(this.getSelf() instanceof CustomCreature creature)) {
            return;
        }

        this.movementAnimations.forEach((animatorName, animationSet) -> {
            creature.claimAnimation(animatorName, this);

            String animation = this.isMoving() ? animationSet.movingAnimation() : animationSet.idleAnimation();

            if (animation == null || animation.isEmpty()) {
                creature.clearRequestedAnimation(animatorName, this);
                return;
            }

            creature.requestAnimation(animatorName, animation, true, this);
        });

        this.animations.forEach((animatorName, possibleAnimations) -> {
            creature.claimAnimation(animatorName, this);

            String animation = this.selectedAnimations.get(animatorName);

            if ((animation == null || animation.isEmpty()) && !possibleAnimations.isEmpty()) {
                animation = chooseRandom(possibleAnimations);
                this.selectedAnimations.put(animatorName, animation);
            }

            if (animation == null || animation.isEmpty()) {
                creature.clearRequestedAnimation(animatorName, this);
                return;
            }

            creature.requestAnimation(animatorName, animation, true, this);
        });
    }

    private void clearAnimations() {
        if (!(this.getSelf() instanceof CustomCreature creature)) {
            return;
        }

        this.animations.keySet().forEach(animatorName -> creature.clearRequestedAnimation(animatorName, this));
        this.movementAnimations.keySet().forEach(animatorName -> creature.clearRequestedAnimation(animatorName, this));
    }

    private void claimAnimations() {
        if (!(this.getSelf() instanceof CustomCreature creature)) {
            return;
        }

        this.animations.keySet().forEach(animatorName -> creature.claimAnimation(animatorName, this));
        this.movementAnimations.keySet().forEach(animatorName -> creature.claimAnimation(animatorName, this));
    }

    private void chooseAnimations() {
        this.selectedAnimations.clear();
        this.animations.forEach((animatorName, possibleAnimations) -> {
            if (!possibleAnimations.isEmpty()) {
                this.selectedAnimations.put(animatorName, chooseRandom(possibleAnimations));
            }
        });
    }

    private String chooseRandom(List<WeightedAnimation> possibleAnimations) {
        if (possibleAnimations.isEmpty()) {
            return null;
        }

        int totalWeight = 0;
        for (WeightedAnimation option : possibleAnimations) {
            if (option.weight() > 0) {
                totalWeight += option.weight();
            }
        }

        if (totalWeight <= 0) {
            return null;
        }

        int roll = this.getSelf().getRandom().nextInt(totalWeight);
        int cursor = 0;

        for (WeightedAnimation option : possibleAnimations) {
            if (option.weight() <= 0) {
                continue;
            }

            cursor += option.weight();
            if (roll < cursor) {
                return option.animation();
            }
        }

        return possibleAnimations.get(0).animation();
    }

    private AnimatedGoal<T> setAnimationOptions(String animatorName, List<WeightedAnimation> possibleAnimations) {
        if (animatorName == null || animatorName.isEmpty()) {
            return this;
        }

        List<WeightedAnimation> validAnimations = new ArrayList<>();
        if (possibleAnimations != null) {
            for (WeightedAnimation animation : possibleAnimations) {
                if (animation != null && animation.animation() != null && !animation.animation().isEmpty() && animation.weight() > 0) {
                    validAnimations.add(animation);
                }
            }
        }

        this.animations.put(animatorName, validAnimations);
        this.movementAnimations.remove(animatorName);
        this.selectedAnimations.remove(animatorName);
        return this;
    }

    private boolean isMoving() {
        return this.getSelf().getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    private String normalizeAnimationName(String animation) {
        return animation == null || animation.isEmpty() ? null : animation;
    }

    private record MovementAnimationSet(String movingAnimation, String idleAnimation) {
    }
}
