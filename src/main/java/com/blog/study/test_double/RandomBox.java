package com.blog.study.test_double;

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
