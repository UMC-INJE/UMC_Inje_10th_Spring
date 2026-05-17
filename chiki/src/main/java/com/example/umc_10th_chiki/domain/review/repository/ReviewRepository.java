package com.example.umc_10th_chiki.domain.review.repository;

import com.example.umc_10th_chiki.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. 커서가 없을 때 (기본 최신순 조회) - 쿼리 자동 생성
    Slice<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 2. 최신순(ID) 기준 페이징 - 쿼리 자동 생성
    Slice<Review> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long id, Pageable pageable);

    // 3. 별점(score) 기준 페이징 - 커스텀 JPQL 필요
    // 조건: 리뷰 점수가 커서 점수보다 작거나, (점수는 같은데 ID가 더 작을 때)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.score < :score OR (r.score = :score AND r.id < :id)) " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdAndCursorScore(
            @Param("memberId") Long memberId,
            @Param("score") Float score,
            @Param("id") Long id,
            Pageable pageable
    );
}