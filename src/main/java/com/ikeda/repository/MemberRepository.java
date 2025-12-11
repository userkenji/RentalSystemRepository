package com.ikeda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikeda.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findByUsername(String username);
}

