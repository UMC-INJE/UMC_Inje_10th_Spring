package com.example.umc10th.domain.category.controller;

import com.example.umc10th.domain.category.dto.CategoryResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public ApiResponse<CategoryResDTO.CategoryListDTO> getCategories() {
        CategoryResDTO.CategoryDTO category = CategoryResDTO.CategoryDTO.builder()
                .categoryId(1L)
                .korea("한식")
                .china("중식")
                .usa("양식")
                .japan("일식")
                .build();

        CategoryResDTO.CategoryListDTO response = CategoryResDTO.CategoryListDTO.builder()
                .categories(List.of(category))
                .build();

        return ApiResponse.onSuccess(response);
    }
}
