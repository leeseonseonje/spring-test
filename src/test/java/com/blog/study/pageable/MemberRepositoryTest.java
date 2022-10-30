package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import com.blog.study.pageable.domain.Team;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import java.util.List;

import static com.blog.study.pageable.domain.QMember.*;
import static com.blog.study.pageable.domain.QTeam.*;

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
            if (i % 2 == 0) {
                Member member = Member.of("memberA", 25);
                member.addTeam(Team.of("teamA"));
                em.persist(member);
            } else {
                Member member = Member.of("memberA", 25);
                memberRepository.save(member);
            }
        }
        em.flush();
        em.clear();


    }

    @Test
    @DisplayName("Spring Data jpa로 페이징 처리 -> select 쿼리 한번, left join일 경우 count쿼리가 자동으로 실행된다")
    void springDataJpaLeftJoin() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.findMembers(pageable);
        assertThat(members.getSize()).isEqualTo(10);
        assertThat(members.getTotalElements()).isEqualTo(120);
        assertThat(members.getTotalPages()).isEqualTo(12);
    }

    @Test
    @DisplayName("inner Join일 경우 countQuery를 별도로 작성해주지 않으면 예외가 발생한다.")
    void spirngDataJpaInnerJoin() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.findTeamInMembers(pageable);
        assertThat(members.getSize()).isEqualTo(10);
        assertThat(members.getTotalElements()).isEqualTo(60);
        assertThat(members.getTotalPages()).isEqualTo(6);
    }

    @Test
    void t() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.members(pageable);
        System.out.println("members.getTotalElements() = " + members.getTotalElements());
        System.out.println("members.getTotalElements() = " + members.getTotalPages());
    }

    @Test
    @DisplayName("Querydsl pageable 사용 -> QueryDSL은 countQuery가 자동으로 실행되지 않는다," +
            " count쿼리를 별도로 수행해야함(수행하지 않을경우 totalPages를 구할 수 없음)")
    void querydslPageableFetch() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<Member> result = queryFactory.selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Page<Member> page = new PageImpl<>(result, pageable, result.size());

        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(10);
    }

    @Test
    @DisplayName("count 쿼리를 별도로 작성하여 페이징 처리")
    void querydslPageableCountQuery() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<Member> result = queryFactory.selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = memberTotalCountQuery();

        Page<Member> page = new PageImpl<>(result, pageable, count);

        assertThat(page.getTotalPages()).isEqualTo(12);
        assertThat(page.getTotalElements()).isEqualTo(120);
    }

    @Test
    @DisplayName("동적 정렬 처리 -> 별도의 count처리 필요")
    void querydslPageableSort() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(DESC, "id"));

        JPAQuery<Member> query = queryFactory.selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }

        List<Member> result = query.fetch();

        Long count = memberTotalCountQuery();

        Page<Member> page = new PageImpl<>(result, pageable, count);

        assertThat(page.getTotalPages()).isEqualTo(12);
        assertThat(page.getTotalElements()).isEqualTo(120);
    }

    private Long memberTotalCountQuery() {
        return queryFactory
                .select(member.count())
                .from(member)
                .fetchOne();
    }
}