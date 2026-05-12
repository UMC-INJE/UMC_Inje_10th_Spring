package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ReviewRequestDTO {
    @Getter
    public static class ReviewDto {
        Long storeId;
        Long missionId;
        String content;
        Float star;
        String image;
    }

    @Getter
    @NoArgsConstructor
    public static class ReplyCreateDto {
        private String content;
    }

    @Getter
    @NoArgsConstructor
    public static class ReviewCreateDto {
        private Long storeId;
        private String content;
        private Float star;
        private List<String> imageUrls;
    }
}
