package com.example.umc10th.domain.user.Repository;

import com.example.umc10th.domain.user.Entity.Mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
