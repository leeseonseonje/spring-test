package com.blog.study.combination;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestReplaceConcrete implements TestInterface {

    private final TestInterface testInterface;

    @Override
    public String test(String message) {
        String replace = message.replace(".", "*");
        return testInterface.test(replace);
    }
}
