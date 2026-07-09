package com.github.syren_dev_tech.scylla.mobs.ai.goals;

import java.util.Random;
import com.github.syren_dev_tech.scylla.mobs.ai.AIGoal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

public class WanderGoal extends AIGoal {
    private final Random random = new Random();
    private int cooldown = 0;
    private double targetX;
    private double targetY;
    private double targetZ;

    public WanderGoal(Mob entity) {
        super(entity);
        this.setWeight(5);
        this.setFlags(java.util.EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public WanderGoal setWeight(int weight) {
        super.setWeight(weight);
        return this;
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }

        if (this.random.nextDouble() >= 0.02) {
            return false;
        }

        var entity = getSelf();
        this.targetX = entity.getX() + (this.random.nextDouble() * 2 - 1) * 8;
        this.targetY = entity.getY() + (this.random.nextDouble() * 2 - 1) * 2;
        this.targetZ = entity.getZ() + (this.random.nextDouble() * 2 - 1) * 8;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        PathNavigation nav = this.getSelf().getNavigation();
        return nav != null && !nav.isDone();
    }

    @Override
    public void start() {
        super.start();

        PathNavigation nav = this.getSelf().getNavigation();
        if (nav != null) {
            nav.moveTo(this.targetX, this.targetY, this.targetZ, 1.0);
        }

        this.cooldown = 100 + this.random.nextInt(200);
    }

    @Override
    public void tick() {
        if (!this.canContinueToUse()) {
            return;
        }
    }

    @Override
    public void stop() {
        super.stop();
    }
}
