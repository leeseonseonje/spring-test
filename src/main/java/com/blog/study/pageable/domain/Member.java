package com.blog.study.pageable.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    private String username;

    private int age;

    @Builder
    private Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public static Member of(String username, int age) {
        return new Member(username, age);
    }

    public void addTeam(Team team) {
        this.team = team;
    }
}
