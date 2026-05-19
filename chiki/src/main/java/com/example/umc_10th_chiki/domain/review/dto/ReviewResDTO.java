package com.example.umc_10th_chiki.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WriteResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    // [7주차 미션] 커서 페이징 전체 틀 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewSliceDTO {
        private List<ReviewDTO> reviewList;
        private Integer listSize;
        private Boolean hasNext;
        private String nextCursor; // 다음 페이지 요청용 커서
    }

    // [7주차 미션] 단건 리뷰 정보 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        private Long reviewId;
        private Float score;
        private String text;
    }
}