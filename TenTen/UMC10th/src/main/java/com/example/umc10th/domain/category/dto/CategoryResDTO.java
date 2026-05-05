package com.example.umc10th.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CategoryResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CategoryDTO {
        private Long categoryId;
        private String korea;
        private String china;
        private String usa;
        private String japan;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CategoryListDTO {
        private List<CategoryDTO> categories;
    }
}
