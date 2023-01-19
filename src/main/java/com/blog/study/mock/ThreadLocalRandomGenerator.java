package com.blog.study.mock;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ThreadLocalRandomGenerator implements RandomGenerator {

    @Override
    public int generate(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
