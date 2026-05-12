package com.example.umc_10th_chiki.domain.store.repository;

import com.example.umc_10th_chiki.domain.store.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}