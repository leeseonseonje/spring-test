package com.blog.study.combination;

import lombok.RequiredArgsConstructor;

import java.util.Locale;

@RequiredArgsConstructor
public class TestUpperCaseConcrete implements TestInterface {

    private final TestInterface testInterface;

    @Override
    public String  test(String message) {
        return testInterface.test(message.toUpperCase(Locale.ROOT));
    }
}
