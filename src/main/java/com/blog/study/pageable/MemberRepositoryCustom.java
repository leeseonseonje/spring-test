package com.blog.study.pageable;

import com.blog.study.pageable.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<Member> members(Pageable pageable);
}
