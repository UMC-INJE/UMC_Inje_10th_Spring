package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewPageDto {
        private Long storeId;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Integer currentPage;
        private Boolean isLast;
        private List<StoreReviewDto> reviewList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewDto {
        private Long reviewId;
        private String reviewerNickname;
        private Float score;
        private String content;
        private String createdAt;
        private List<String> photoUrls;
        private ReplyDto reply;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyDto {
        private Long replyId;
        private String content;
        private String createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyResultDto {
        private Long reviewId;
        private Long replyId;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberReviewPageDto {
        private List<MemberReviewDto> reviewList;
        private Integer listSize;
        private Long nextCursor; // 다음 커서 값
        private Boolean hasNext; // 다음 페이지 여부
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberReviewDto {
        private Long reviewId;
        private String storeName;
        private Float score;
        private String content;
        private String createdAt;
    }
}
