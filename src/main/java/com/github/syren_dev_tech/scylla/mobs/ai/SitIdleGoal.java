package com.github.syren_dev_tech.scylla.mobs.ai;

import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import net.minecraft.world.entity.Mob;

public class SitIdleGoal extends AIGoal {

    private boolean bored = false;
    private int boredomTicksMod = 100; // 20 * 5 seconds
    private float chanceToGetBored = 0.1F;

    public SitIdleGoal(Mob self) {
        super(self);
    }

    @Override
    public boolean canUse() {
        return !this.self.isSwimming();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.bored) {
            return false;
        }

        return !this.self.isSwimming();
    }

    @Override
    public void start() {
        ScyllaCommon.LOGGER.debug("Starting SitIdleGoal for {}", this.self.getName().getString());

        this.self.getNavigation().stop();
        this.bored = false;

        // If this is a custom creature with animators, force idle animations from the builder
        if (this.self instanceof CustomCreature creature) {
            creature.clearAllRequestedAnimations();

            var animators = creature.getState().getBuilder().getAnimators();
            animators.forEach((name, handler) -> {
                var res = handler.apply(null, creature.getState());
                if (res != null && res.animation() != null && !res.animation().isEmpty()) {
                    // Force the animator to play the chosen animation in loop (idle)
                    creature.requestAnimation(name, res.animation(), true);
                }
            });
        }
    }

    @Override
    public void stop() {
        ScyllaCommon.LOGGER.debug("Stopping SitIdleGoal for {}", this.self.getName().getString());

        if (this.self instanceof CustomCreature creature) {
            creature.clearAllRequestedAnimations();
        }

    }

    @Override
    public void tick() {
        // Check on 0th tick if the mob's been idle long enough
        if (this.self.tickCount % this.boredomTicksMod == 0) {
            this.bored = this.self.getRandom().nextFloat() < this.chanceToGetBored;
        }
    }
}
