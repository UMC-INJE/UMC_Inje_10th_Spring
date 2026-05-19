package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDTO {
        private String content;
        private Float star;
    }
    @Getter
    @Setter
    public static class MyReviewListDTO {
        private String sortBy = "id";
        private Long cursorId;
        private Float cursorStar;
    }
}
