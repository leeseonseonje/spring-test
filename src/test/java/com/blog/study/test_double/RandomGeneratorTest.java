package com.blog.study.test_double;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomGeneratorTest {

    @Test
    void random_integer() {
        RandomGenerator randomGenerator = new ThreadLocalRandomGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.println(randomGenerator.generate(100));
        }
    }

    @Test
    void mock_random_generator() {
        RandomGenerator mock = new MockRandomGenerator();

        Assertions.assertThat(mock.generate(5)).isEqualTo(5);
    }

    static class MockRandomGenerator implements RandomGenerator {

        @Override
        public int generate(int bound) {
            return bound;
        }
    }
}

//mock