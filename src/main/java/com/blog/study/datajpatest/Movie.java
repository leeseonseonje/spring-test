package com.blog.study.datajpatest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    public Movie(String title) {
        this.title = title;
    }
}
