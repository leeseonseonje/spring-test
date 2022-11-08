package com.blog.study.datajpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

@DataJpaQueryFactoryTest
class ItemServiceTest {

    static class ItemSearchMock implements ItemSearch {

        @Override
        public boolean search(TYPE type, String title) {
            return false;
        }
    }

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MovieRepository movieRepository;

    ItemSearch itemSearch = new ItemSearchMock();

    @Test
    void book_and_movie_save() {
        ItemService sut = new ItemService(itemSearch, bookRepository, movieRepository);

        boolean result = sut.itemSave();

        assertThat(result).isFalse();
    }
}