package com.blog.study.datajpatest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blog.study.datajpatest.TYPE.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemSearch itemSearch;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;

    public boolean itemSave() {

        boolean isBook = saveBook();

        boolean isMovie = saveMovie();

        return isBook && isMovie;
    }

    private boolean saveBook() {
        boolean search = itemSearch.search(BOOK, "토비의 스프링");
        if (search) {
            Book book = new Book("토비의 스프링");
            bookRepository.save(book);
        }
        return search;
    }

    private boolean saveMovie() {
        boolean search = itemSearch.search(MOVIE, "극한 직업");
        if (search) {
            Movie movie = new Movie("극한 직업");
            movieRepository.save(movie);
        }
        return search;
    }
}
