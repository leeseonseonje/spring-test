package com.blog.study.combination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinationTest {

    @Test
    void combination() {
        TestInterface sut = new TestConcrete();
        sut = new TestReplaceConcrete(sut);
        sut = new TestUpperCaseConcrete(sut);

        String result = sut.test("...seon");
        System.out.println(result);
    }
}