package com.github.syren_dev_tech.scylla.mobs.ai.goals;

import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class SitIdleGoal extends AIGoal {

    private boolean bored = false;
    private int boredomTicksMod = 100; // 20 * 5 seconds
    private float chanceToGetBored = 0.1F;

    public SitIdleGoal(Mob self) {
        super(self);
        this.setWeight(10);
        this.setFlags(java.util.EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !this.getSelf().isSwimming();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.bored) {
            return false;
        }

        return !this.getSelf().isSwimming();
    }

    @Override
    public void start() {
        super.start();
        ScyllaCommon.LOGGER.debug("Starting SitIdleGoal for {}", this.getSelf().getName().getString());

        this.getSelf().getNavigation().stop();
        this.bored = false;
    }

    @Override
    public void stop() {
        super.stop();
        ScyllaCommon.LOGGER.debug("Stopping SitIdleGoal for {}", this.getSelf().getName().getString());
    }

    @Override
    public void tick() {
        // Check on 0th tick if the mob's been idle long enough
        if (this.getSelf().tickCount % this.boredomTicksMod == 0) {
            this.bored = this.getSelf().getRandom().nextFloat() < this.chanceToGetBored;
        }
    }
}
