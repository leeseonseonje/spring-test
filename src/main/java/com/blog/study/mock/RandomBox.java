package com.blog.study.mock;

import java.util.concurrent.ThreadLocalRandom;

public class RandomBox {

    private final RandomGenerator randomGenerator;

    public RandomBox(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public Weapon weaponRandomBox() {
        int random = randomGenerator.generate(Weapon.values().length);
        return Weapon.values()[random];
    }
}
