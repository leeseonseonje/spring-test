package com.blog.study.di;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DeveloperTest {

    @Autowired
    Developer developer;

    @Test
    void develop() {
        assertThat(developer.develop()).isEqualTo("SpringBoot");
    }
}