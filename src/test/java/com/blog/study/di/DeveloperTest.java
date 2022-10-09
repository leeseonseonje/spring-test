package com.blog.study.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeveloperTest {

    @Autowired
    Developer developer;

    @Test
    void develop() {
        developer.develop();
    }
}