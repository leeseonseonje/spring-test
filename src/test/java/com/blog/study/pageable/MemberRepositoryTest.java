package com.blog.study.pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;

import java.util.List;

@DataJpaTest
class MemberRepositoryTest {

    @TestConfiguration
    static class TestQueryFactory {

        @Autowired
        EntityManager em;

        @Bean
        public JPAQueryFactory queryFactory() {
            return new JPAQueryFactory(em);
        }
    }

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
        for (int i = 0; i < 100; i++) {
            Member member = Member.of("memberA", 25);
            memberRepository.save(member);
        }
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("Spring Data jpa로 페이징 처리")
    void springDataJapPageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.findMembers(pageable);
        System.out.println(members.getContent().size());
        System.out.println(members.getTotalElements());
        System.out.println(members.getTotalPages());
        System.out.println(members.getSize());
        System.out.println(members.getNumber());
        System.out.println(members.getNumberOfElements());
    }
}