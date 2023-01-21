package com.blog.study.test_double;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ThreadLocalRandomGenerator implements RandomGenerator {

    @Override
    public int generate(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
