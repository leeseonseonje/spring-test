package com.blog.study.mock;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RandomBoxTest {

    @Test
    void weapon_random_box() {
        RandomBox randomBox = new RandomBox(new StubRandomGenerator());
        Weapon weapon = randomBox.weaponRandomBox();
        assertThat(weapon).isEqualTo(Weapon.SWORD);
    }
}