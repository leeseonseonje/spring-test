package com.blog.study.learn.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class B {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_id")
    private A a;

    public B(String name) {
        this.name = name;
    }
}
