package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import java.util.HashMap;
import java.util.Map;

public class CreatureState<T extends CustomCreature> {

    private final CreatureBuilder<T> builder;
    private int ticksEllapsed = 0;
    private final Map<String, AnimationDefinition> forcedAnimations = new HashMap<>();
    private final Map<String, Animator<T>> instanceAnimators = new HashMap<>();

    public CreatureState(CreatureBuilder<T> builder) {
        this.builder = builder;
    }

    public int getEllapsedTicks() {
        return this.ticksEllapsed;
    }

    public void incrementTicks() {
        this.ticksEllapsed++;
    }

    public void resetTicks() {
        this.ticksEllapsed = 0;
    }

    public String[] getCurrentAnimations() {
        return this.instanceAnimators.values().stream().map(Animator::getCurrentAnimation).toArray(String[]::new);
    }

    public String getCurrentAnimation(String animatorName) {
        Animator<T> animator = this.instanceAnimators.get(animatorName);
        if (animator == null) {
            return "";
        }

        return animator.getCurrentAnimation();
    }

    public CreatureBuilder<T> getBuilder() {
        return builder;
    }

    public void setForcedAnimation(String animatorName, String animation, boolean loop) {
        if (animatorName == null || animatorName.isEmpty())
            return;
        if (animation == null || animation.isEmpty()) {
            this.forcedAnimations.remove(animatorName);
            return;
        }

        this.forcedAnimations.put(animatorName, new AnimationDefinition(animation, loop));
    }

    public AnimationDefinition getForcedAnimation(String animatorName) {
        return this.forcedAnimations.get(animatorName);
    }

    public void clearForcedAnimation(String animatorName) {
        this.forcedAnimations.remove(animatorName);
    }

    public void clearAllForcedAnimations() {
        this.forcedAnimations.clear();
    }

    public void setAnimator(String name, Animator<T> animator) {
        if (name == null || name.isEmpty() || animator == null)
            return;
        this.instanceAnimators.put(name, animator);
    }

    public Animator<T> getAnimator(String name) {
        return this.instanceAnimators.get(name);
    }
}
