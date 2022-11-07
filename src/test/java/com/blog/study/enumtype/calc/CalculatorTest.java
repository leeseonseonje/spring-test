package com.blog.study.enumtype.calc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void calc() {
        assertThat(Calculator.PLUS.getCalculation().doCalc(5, 5)).isEqualTo(10);
        assertThat(Calculator.MINUS.getCalculation().doCalc(5, 5)).isEqualTo(0);
        assertThat(Calculator.MULTIPLY.getCalculation().doCalc(5, 5)).isEqualTo(25);
        assertThat(Calculator.DIVIDE.getCalculation().doCalc(5, 5)).isEqualTo(1);
    }
}
