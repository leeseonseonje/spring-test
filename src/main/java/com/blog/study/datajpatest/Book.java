package com.blog.study.datajpatest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    public Book(String title) {
        this.title = title;
    }
}
