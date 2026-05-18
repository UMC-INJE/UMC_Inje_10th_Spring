package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {

        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static ReviewResponseDTO.MyReviewCursorListDTO toMyReviewCursorListDTO(Slice<Review> reviewSlice) {

        List<ReviewResponseDTO.ReviewPreViewDTO> dtoList = reviewSlice.stream()
                .map(ReviewConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        Long nextCursorId = dtoList.isEmpty() ? null : reviewSlice.getContent().get(dtoList.size() - 1).getId();
        Float nextCursorStar = dtoList.isEmpty() ? null : reviewSlice.getContent().get(dtoList.size() - 1).getStar();

        return ReviewResponseDTO.MyReviewCursorListDTO.builder()
                .reviewList(dtoList)
                .listSize(dtoList.size())
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .hasNext(reviewSlice.hasNext())
                .build();
    }
}