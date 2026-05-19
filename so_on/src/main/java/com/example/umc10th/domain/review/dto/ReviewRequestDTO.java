package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ReviewRequestDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDto {
        @NotNull(message = "가게 ID는 필수입니다.")
        Long storeId;

        Long missionId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content;

        @NotNull(message = "별점은 필수입니다.")
        Float star;

        String image;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyCreateDto {
        @NotBlank(message = "답글 내용은 필수입니다.")
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCreateDto {
        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        @NotNull(message = "별점은 필수입니다.")
        private Float star;

        private List<String> imageUrls;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberReviewRequestDto {
        private Long cursor; // 마지막으로 조회한 리뷰 ID 또는 별점 값

        @NotBlank(message = "정렬 기준은 필수입니다. (id 또는 star)")
        private String query; // "id" 또는 "star"

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Positive(message = "페이지 크기는 양수여야 합니다.")
        private Integer pageSize;
    }
}
