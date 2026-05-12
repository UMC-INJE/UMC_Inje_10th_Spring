package com.example.umc10th.domain.review.Repository;

import com.example.umc10th.domain.review.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    boolean existsByReviewId(Long reviewId);

    Optional<Reply> findFirstByReviewIdOrderByIdDesc(Long reviewId);
}
