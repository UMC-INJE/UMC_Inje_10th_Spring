package com.example.umc_10th_chiki.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.umc_10th_chiki.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
