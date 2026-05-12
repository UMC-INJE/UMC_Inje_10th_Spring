package com.example.umc10th.domain.owner.Repository;

import com.example.umc10th.domain.owner.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
