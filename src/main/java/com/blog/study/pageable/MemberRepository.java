package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Book m where m.username = :memberName")
    Page<Member> findMembers(String memberName, Pageable pageable);


    @Query(value = "select new com.blog.study.pageable.MemberDto(m.id, t.id)" +
            " from Book m join m.team t")
    Page<MemberDto> findTeamInMembers(Pageable pageable);

}
