package com.github.syren_dev_tech.scylla.common.mobs.creatures;

import com.github.syren_dev_tech.scylla.common.mobs.CreatureBuilder;

public class CreatureState<T extends CustomCreature> {

    private final CreatureBuilder<T> builder;
    private int ticksEllapsed = 0;

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
        return this.builder.getAnimators().values().stream().map(Animator::getCurrentAnimation).toArray(String[]::new);
    }

    public String getCurrentAnimation(String animatorName) {
        Animator animator = this.builder.getAnimators().get(animatorName);
        if (animator == null) {
            return "";
        }

        return animator.getCurrentAnimation();
    }
}
