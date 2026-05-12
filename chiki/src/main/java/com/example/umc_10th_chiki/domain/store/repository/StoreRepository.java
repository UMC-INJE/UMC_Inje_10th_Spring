package com.example.umc_10th_chiki.domain.store.repository;

import com.example.umc_10th_chiki.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}