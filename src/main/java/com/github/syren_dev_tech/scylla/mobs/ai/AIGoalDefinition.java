package com.github.syren_dev_tech.scylla.mobs.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;

public class AIGoalDefinition<T extends CustomCreature> {

    private final Function<T, AIGoal> goalFactory;
    private final List<WeightedAnimation> animations;
    private final String animatorName;

    private AIGoalDefinition(Function<T, AIGoal> goalFactory, String animatorName, List<WeightedAnimation> animations) {
        this.goalFactory = goalFactory;
        this.animatorName = animatorName;
        this.animations = animations;
    }

    public static <T extends CustomCreature> Builder<T> define(Function<T, AIGoal> goalFactory) {
        return new Builder<>(goalFactory);
    }

    public Function<T, AIGoal> getGoalFactory() {
        return goalFactory;
    }

    public String getAnimatorName() {
        return animatorName;
    }

    public List<WeightedAnimation> getAnimations() {
        return animations;
    }

    public boolean hasAnimatorBinding() {
        return animatorName != null && !animatorName.isEmpty() && !animations.isEmpty();
    }

    @SafeVarargs
    public static List<WeightedAnimation> weightedPool(WeightedAnimation... animations) {
        List<WeightedAnimation> result = new ArrayList<>();

        if (animations != null) {
            Collections.addAll(result, animations);
        }

        return result;
    }

    public static List<WeightedAnimation> pool(String... animations) {
        List<WeightedAnimation> result = new ArrayList<>();

        if (animations == null) {
            return result;
        }

        for (String animation : animations) {
            if (animation != null && !animation.isEmpty()) {
                result.add(WeightedAnimation.of(animation));
            }
        }

        return result;
    }

    public static WeightedAnimation w(String animation, int weight) {
        return WeightedAnimation.weighted(animation, weight);
    }

    public static record WeightedAnimation(String animation, int weight) {
        public WeightedAnimation {
            if (animation == null || animation.isEmpty()) {
                throw new IllegalArgumentException("Animation name cannot be null or empty");
            }

            if (weight <= 0) {
                throw new IllegalArgumentException("Animation weight must be greater than zero");
            }
        }

        public static WeightedAnimation of(String animation) {
            return new WeightedAnimation(animation, 1);
        }

        public static WeightedAnimation weighted(String animation, int weight) {
            return new WeightedAnimation(animation, weight);
        }
    }

    public static class Builder<T extends CustomCreature> {
        private final Function<T, AIGoal> goalFactory;
        private String animatorName;
        private final List<WeightedAnimation> animations = new ArrayList<>();

        private Builder(Function<T, AIGoal> goalFactory) {
            this.goalFactory = goalFactory;
        }

        public Builder<T> withAnimator(String animatorName) {
            this.animatorName = animatorName;
            return this;
        }

        public Builder<T> withAnimator(String animatorName, String... animationNames) {
            this.animatorName = animatorName;
            return this.withAnimations(animationNames);
        }

        public Builder<T> withAnimator(String animatorName, List<WeightedAnimation> animations) {
            this.animatorName = animatorName;
            return this.withWeightedAnimations(animations);
        }

        public Builder<T> withAnimation(String animationName) {
            this.animations.add(WeightedAnimation.of(animationName));
            return this;
        }

        public Builder<T> withAnimation(String animationName, int weight) {
            this.animations.add(WeightedAnimation.weighted(animationName, weight));
            return this;
        }

        public Builder<T> withAnimations(String... animationNames) {
            if (animationNames == null) {
                return this;
            }

            for (String animationName : animationNames) {
                if (animationName != null && !animationName.isEmpty()) {
                    this.animations.add(WeightedAnimation.of(animationName));
                }
            }

            return this;
        }

        public Builder<T> withWeightedAnimations(List<WeightedAnimation> animations) {
            if (animations == null) {
                return this;
            }

            this.animations.addAll(animations);
            return this;
        }

        public AIGoalDefinition<T> build() {
            List<WeightedAnimation> animationPool = Collections.unmodifiableList(new ArrayList<>(this.animations));
            return new AIGoalDefinition<>(this.goalFactory, this.animatorName, animationPool);
        }
    }
}
