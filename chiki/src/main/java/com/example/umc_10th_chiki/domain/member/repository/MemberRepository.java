package com.example.umc_10th_chiki.domain.member.repository;

import com.example.umc_10th_chiki.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}