package com.blog.study.pageable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

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
}
