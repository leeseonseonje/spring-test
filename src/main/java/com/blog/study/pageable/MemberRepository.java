package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {


    @EntityGraph(attributePaths = {"team"})
    @Query(value = "select m from Member m")
    Page<Member> findMembers(Pageable pageable);

    @Query(value = "select m from Member m join fetch m.team",
            countQuery = "select count(m) from Member m join m.team")
    Page<Member> findTeamInMembers(Pageable pageable);
}
