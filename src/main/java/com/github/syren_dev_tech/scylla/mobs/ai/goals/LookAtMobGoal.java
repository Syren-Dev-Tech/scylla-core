package com.github.syren_dev_tech.scylla.mobs.ai.goals;

import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

// AI goal that makes the host mob look at another entity, including players
public class LookAtMobGoal extends AIGoal {

    private Entity lookTarget;
    private int ticksSpentLooking;
    private int maxLookingTicks = 40;
    private float maxSightDistance = 6.0F;
    private float chanceToStartLooking = 0.02F;

    public LookAtMobGoal(Mob self) {
        super(self);
    }

    public LookAtMobGoal(Mob self, float maxSightDistance) {
        this(self);
        this.maxSightDistance = maxSightDistance;
    }

    public LookAtMobGoal(Mob self, int maxLookingTicks) {
        this(self);
        this.maxLookingTicks = maxLookingTicks;
    }

    public LookAtMobGoal(Mob self, float maxSightDistance, int maxLookingTicks) {
        this(self);
        this.maxSightDistance = maxSightDistance;
        this.maxLookingTicks = maxLookingTicks;
    }

    @Override
    public boolean canUse() {
        if (this.getSelf().getRandom().nextFloat() >= this.chanceToStartLooking) {
            return false;
        }

        if (this.getSelf().getTarget() != null) {
            this.lookTarget = this.getSelf().getTarget();
        }

        return this.lookTarget != null && this.lookTarget.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.lookTarget == null || !this.lookTarget.isAlive()) {
            return false;
        }

        // If the looking target is further than the max sight distance, stop looking
        double distanceSq = this.getSelf().distanceToSqr(this.lookTarget);
        if (distanceSq > this.maxSightDistance * this.maxSightDistance) {
            return false;
        }

        return this.ticksSpentLooking < this.maxLookingTicks;
    }

    @Override
    public void start() {
        super.start();
        ScyllaCommon.LOGGER.debug("Starting LookAtMobGoal for {} looking at {}", this.getSelf().getName().getString(), this.lookTarget.getName().getString());

        this.ticksSpentLooking = 0;
        this.maxLookingTicks = this.adjustedTickDelay(40 + this.getSelf().getRandom().nextInt(40));
    }

    @Override
    public void stop() {
        super.stop();
        ScyllaCommon.LOGGER.debug("Stopping LookAtMobGoal for {} looking at {}", this.getSelf().getName().getString(), this.lookTarget != null ? this.lookTarget.getName().getString() : "null");

        this.lookTarget = null;
        this.ticksSpentLooking = 0;
    }

    @Override
    public void tick() {
        if (this.lookTarget != null) {
            this.ticksSpentLooking++;
        }
    }
}
