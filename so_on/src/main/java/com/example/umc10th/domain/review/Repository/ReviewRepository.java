package com.example.umc10th.domain.review.Repository;

import com.example.umc10th.domain.review.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByUserId(Long userId);

    @Query(
            value = """
                    select r
                    from Review r
                    join fetch r.user u
                    join r.store s
                    join s.owner o
                    where s.id = :storeId and o.id = :ownerId
                    order by r.id desc
                    """,
            countQuery = """
                    select count(r)
                    from Review r
                    join r.store s
                    join s.owner o
                    where s.id = :storeId and o.id = :ownerId
                    """
    )
    Page<Review> findStoreReviews(@Param("ownerId") Long ownerId, @Param("storeId") Long storeId, Pageable pageable);

    @Query("""
            select r
            from Review r
            join fetch r.store s
            join fetch s.owner o
            join fetch r.user u
            where r.id = :reviewId and o.id = :ownerId
            """)
    Optional<Review> findReviewDetail(@Param("ownerId") Long ownerId, @Param("reviewId") Long reviewId);
}
