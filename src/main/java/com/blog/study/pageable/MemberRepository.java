package com.blog.study.pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {


    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    Page<Member> findMembers(Pageable pageable);
}
