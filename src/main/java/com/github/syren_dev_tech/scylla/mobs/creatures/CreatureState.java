package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import java.util.HashMap;
import java.util.Map;

public class CreatureState<T extends CustomCreature> {

    private final CreatureBuilder<T> builder;
    private int ticksEllapsed = 0;
    private final Map<String, AnimationDefinition> forcedAnimations = new HashMap<>();
    private final Map<String, Object> forcedAnimationOwners = new HashMap<>();
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

    public boolean isCurrentAnimation(String animatorName, String animationName) {
        if (animationName == null || animationName.isEmpty()) {
            return false;
        }

        return animationName.equals(this.getCurrentAnimation(animatorName));
    }

    public boolean isCurrentAnimation(String animatorName, String... animationNames) {
        String current = this.getCurrentAnimation(animatorName);
        if (current.isEmpty() || animationNames == null || animationNames.length == 0) {
            return false;
        }

        for (String animationName : animationNames) {
            if (current.equals(animationName)) {
                return true;
            }
        }

        return false;
    }

    public CreatureBuilder<T> getBuilder() {
        return builder;
    }

    public void setForcedAnimation(String animatorName, String animation, boolean loop) {
        this.setForcedAnimation(animatorName, animation, loop, null);
    }

    public boolean setForcedAnimation(String animatorName, String animation, boolean loop, Object owner) {
        if (animatorName == null || animatorName.isEmpty())
            return false;

        Object currentOwner = this.forcedAnimationOwners.get(animatorName);
        if (owner != null && currentOwner != null && currentOwner != owner) {
            return false;
        }

        if (animation == null || animation.isEmpty()) {
            this.clearForcedAnimation(animatorName, owner);
            return true;
        }

        this.forcedAnimations.put(animatorName, new AnimationDefinition(animation, loop));

        if (owner != null) {
            this.forcedAnimationOwners.put(animatorName, owner);
        }

        return true;
    }

    public AnimationDefinition getForcedAnimation(String animatorName) {
        return this.forcedAnimations.get(animatorName);
    }

    public Map<String, AnimationDefinition> getForcedAnimationsSnapshot() {
        return new HashMap<>(this.forcedAnimations);
    }

    public void clearForcedAnimation(String animatorName) {
        this.clearForcedAnimation(animatorName, null);
    }

    public boolean clearForcedAnimation(String animatorName, Object owner) {
        if (animatorName == null || animatorName.isEmpty()) {
            return false;
        }

        if (owner != null) {
            Object currentOwner = this.forcedAnimationOwners.get(animatorName);
            if (currentOwner != null && currentOwner != owner) {
                return false;
            }
        }

        this.forcedAnimations.remove(animatorName);
        this.forcedAnimationOwners.remove(animatorName);
        return true;
    }

    public void clearAllForcedAnimations() {
        this.forcedAnimations.clear();
        this.forcedAnimationOwners.clear();
    }

    public void replaceForcedAnimations(Map<String, AnimationDefinition> animations) {
        this.forcedAnimations.clear();
        this.forcedAnimationOwners.clear();

        if (animations != null) {
            this.forcedAnimations.putAll(animations);
        }
    }

    public void claimForcedAnimationOwner(String animatorName, Object owner) {
        if (animatorName == null || animatorName.isEmpty() || owner == null) {
            return;
        }

        this.forcedAnimations.remove(animatorName);
        this.forcedAnimationOwners.put(animatorName, owner);
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
