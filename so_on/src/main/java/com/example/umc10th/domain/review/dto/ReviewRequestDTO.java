package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    public static class ReviewDto {
        Long storeId;
        Long missionId;
        String content;
        Float star;
        String image;
    }
}
