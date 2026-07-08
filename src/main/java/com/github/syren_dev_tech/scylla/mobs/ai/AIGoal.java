package com.github.syren_dev_tech.scylla.mobs.ai;

import com.github.syren_dev_tech.scylla.ScyllaCommon;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class AIGoal extends Goal {

    private boolean enabled = true;
    final Mob self;
    private int weight;
    boolean isDelegate = false;

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
    public void start() {
        if (!isDelegate) {
            ScyllaCommon.LOGGER.debug("Entity {} changing to goal {}", this.self.getId(), getClass().getSimpleName());
        }
    }

    @Override
    public void stop() {
        if (!isDelegate) {
            ScyllaCommon.LOGGER.debug("Entity {} goal {} stopped", this.self.getId(), getClass().getSimpleName());
        }
    }

    @Override
    public boolean canUse() {
        return this.enabled;
    }
}
