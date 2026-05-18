package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.StoreReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {

    Page<StoreReview> findAllByUser_UserId(
            Long userId,
            Pageable pageable
    );
}