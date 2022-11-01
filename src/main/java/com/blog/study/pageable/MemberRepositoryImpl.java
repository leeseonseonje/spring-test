package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import static com.blog.study.pageable.domain.QMember.*;
import static com.blog.study.pageable.domain.QTeam.*;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> members(Pageable pageable) {
        JPAQuery<Member> query = queryFactory.selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        sort(pageable, query);

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchOne);
    }

    private void sort(Pageable pageable, JPAQuery<Member> query) {
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())));
        }
    }
}
