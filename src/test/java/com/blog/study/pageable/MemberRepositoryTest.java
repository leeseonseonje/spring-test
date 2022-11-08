package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import com.blog.study.pageable.domain.Team;
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
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.*;

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

    @Autowired
    JPAQueryFactory queryFactory;

    @BeforeEach
    void init() {
        for (int i = 0; i < 120; i++) {
            Member member = Member.of("memberA", 25);
            if (i % 2 == 0) {
                member.addTeam(Team.of("teamA"));
            }
            em.persist(member);
        }
        em.flush();
        em.clear();


    }

    @Test
    @DisplayName("Spring Data jpa로 페이징 처리 -> select 쿼리 한번, @EntityGraph사용할 경우 count쿼리가 자동으로 실행된다")
    void spring_data_jpa_paging_entityGraph() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(ASC, "id"));

        Page<Member> members = memberRepository.findMembers("memberA", pageable);

        assertThat(members.getSize()).isEqualTo(10);
        assertThat(members.getTotalElements()).isEqualTo(120);
        assertThat(members.getTotalPages()).isEqualTo(12);
    }

    @Test
    @DisplayName("페치 조인을 사용할 경우 countQuery를 별도로 작성해주지 않으면 예외가 발생한다.")
    void spring_data_jpa_paging_fetchJoin() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(DESC, "id"));
        Page<MemberDto> members = memberRepository.findTeamInMembers(pageable);
        assertThat(members.getSize()).isEqualTo(10);
        assertThat(members.getTotalElements()).isEqualTo(60);
        assertThat(members.getTotalPages()).isEqualTo(6);
    }

    @Test
    @DisplayName("querydsl에서는 반드시 별도의 count 쿼리를 작성")
    void querydsl_pageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.members(pageable);
        System.out.println("members.getTotalElements() = " + members.getTotalElements());
        System.out.println("members.getTotalElements() = " + members.getTotalPages());
    }
}