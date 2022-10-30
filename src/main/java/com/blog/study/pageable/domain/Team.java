package com.blog.study.pageable.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Team(String name) {
        this.name = name;
    }

    public static Team of(String name) {
        return new Team(name);
    }
}
