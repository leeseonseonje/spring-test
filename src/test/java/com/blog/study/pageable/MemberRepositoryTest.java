package com.blog.study.pageable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberRepositoryQuerydsl memberRepositoryQuerydsl;

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

    @Test
    void t() {
        List<Member> members = memberRepositoryQuerydsl.saveMembers();
        System.out.println("members.size() = " + members.size());
    }
}