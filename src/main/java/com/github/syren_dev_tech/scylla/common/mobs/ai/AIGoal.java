package com.github.syren_dev_tech.scylla.common.mobs.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class AIGoal extends Goal {

    private boolean enabled = true;
    final Mob self;
    private int weight;

    public AIGoal(Mob self) {
        super();
        this.self = self;
        this.weight = 1;
    }

    public AIGoal setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean canUse() {
        return this.enabled;
    }
}
