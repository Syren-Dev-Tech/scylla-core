package com.github.syren_dev_tech.scylla.mobs.creatures;

public record AnimationDefinition(String animation, boolean loop) {

    public static AnimationDefinition loop(String animation) {
        return new AnimationDefinition(animation, true);
    }

    public static AnimationDefinition once(String animation) {
        return new AnimationDefinition(animation, false);
    }
}
