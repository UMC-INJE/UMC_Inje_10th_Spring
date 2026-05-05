
package com.example.umc_10th_chiki.domain.review.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.umc_10th_chiki.domain.review.entity.Review;



@Repository

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

