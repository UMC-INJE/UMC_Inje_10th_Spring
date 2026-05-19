package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice; // 임포트 필수
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByMember(Member member, Pageable pageable);

    // 미션 추가 ID 순 최신순 페이징
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member = :member " +
            "AND (:cursorId IS NULL OR r.id < :cursorId) ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(@Param("member") Member member, @Param("cursorId") Long cursorId, Pageable pageable);

    // 미션 추가 별점 순 커서 페이징 (별점이 같으면 최신 ID 순 정렬)
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member = :member " +
            "AND (:cursorStar IS NULL OR r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByStarCursor(@Param("member") Member member, @Param("cursorStar") Float cursorStar, @Param("cursorId") Long cursorId, Pageable pageable);
}