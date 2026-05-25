package com.example.umc10th.domain.user.Repository;

import com.example.umc10th.domain.user.Entity.Mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
