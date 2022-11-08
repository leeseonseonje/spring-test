package com.blog.study.datajpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ItemServiceSpringBootTest {

    @Autowired
    ItemService itemService;

    @Test
    void book_and_movie_save() {
        boolean result = itemService.itemSave();

        assertThat(result).isTrue();
    }
}
