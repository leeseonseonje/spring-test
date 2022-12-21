package com.blog.study.learn.one_to_one;

import com.blog.study.datajpatest.DataJpaQueryFactoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@DataJpaQueryFactoryTest
public class OneToOneTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
        A a = new A("A");
        B b = new B("B");
        b.setA(a);

        B newB = new B("newB");
        em.persist(a);
        em.persist(b);
        em.persist(newB);

        em.flush();
        em.clear();
    }
    @Test
    void set_join() {
        B b = em.find(B.class, 2L);
        B newB = em.find(B.class, 3L);

        A a = em.find(A.class, 1L);
        newB.setA(a);

        em.flush();
        em.clear();


        B result = em.find(B.class, 2L);
        B result2 = em.find(B.class, 3L);
        System.out.println(result.getA().getName());
        System.out.println(result2.getA().getName());
    }
}
