package com.blog.study.datajpatest;

import com.blog.study.pageable.JpaQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(JpaQueryFactory.class)
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