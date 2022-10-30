package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import com.blog.study.pageable.domain.QMember;
import com.blog.study.pageable.domain.QTeam;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.blog.study.pageable.domain.QMember.*;
import static com.blog.study.pageable.domain.QTeam.*;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> members(Pageable pageable) {
        JPAQuery<Member> query = queryFactory.selectFrom(member)
                .join(member.team, team).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }

        List<Member> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
