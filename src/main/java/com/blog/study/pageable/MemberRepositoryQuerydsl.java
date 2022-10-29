package com.blog.study.pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    public List<Member> saveMembers() {
        return queryFactory
                .selectFrom(QMember.member)
                .fetch();
    }
}
