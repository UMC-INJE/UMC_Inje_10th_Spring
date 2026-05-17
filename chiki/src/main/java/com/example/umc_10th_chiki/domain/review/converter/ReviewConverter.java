package com.example.umc_10th_chiki.domain.review.converter;

import com.example.umc_10th_chiki.domain.review.dto.ReviewResDTO;
import com.example.umc_10th_chiki.domain.review.entity.Review;
import org.springframework.data.domain.Slice;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewSliceDTO toReviewSliceDTO(Slice<Review> reviewSlice, String sortType) {
        List<ReviewResDTO.ReviewDTO> reviewDTOList = reviewSlice.stream()
                .map(review -> ReviewResDTO.ReviewDTO.builder()
                        .reviewId(review.getId())
                        .score(review.getScore())
                        .text(review.getText())
                        .build())
                .collect(Collectors.toList());

        String nextCursor = null;
        if (reviewSlice.hasNext() && !reviewDTOList.isEmpty()) {
            ReviewResDTO.ReviewDTO lastReview = reviewDTOList.get(reviewDTOList.size() - 1);

            if ("score".equals(sortType)) {
                nextCursor = "score:" + lastReview.getScore() + "_" + lastReview.getReviewId();
            } else {
                nextCursor = "id:" + lastReview.getReviewId();
            }
        }
        return ReviewResDTO.ReviewSliceDTO.builder()
                .reviewList(reviewDTOList)
                .listSize(reviewDTOList.size())
                .hasNext(reviewSlice.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}