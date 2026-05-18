package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.mission.entity.MyMission;
import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.entity.StoreReview;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public class StoreConverter {

    public static StoreReview toStoreReview(
            Store store,
            User user,
            MyMission myMission,
            StoreReqDTO.CreateReviewDTO request
    ) {
        return StoreReview.builder()
                .store(store)
                .user(user)
                .myMission(myMission)
                .storeReviewRating(request.getStoreReviewRating())
                .storeReviewContent(request.getStoreReviewContent())
                .build();
    }
    public static StoreResDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(StoreReview review){

        return StoreResDTO.MyReviewPreviewDTO.builder()
                .storeName(review.getStore().getStoreName())
                .storeReviewRating(review.getStoreReviewRating())
                .storeReviewContent(review.getStoreReviewContent())
                .build();
    }
    public static StoreResDTO.MyReviewPageDTO toMyReviewPageDTO(Page<StoreReview> reviewPage){

        List<StoreResDTO.MyReviewPreviewDTO> reviewList =
                reviewPage.stream()
                        .map(StoreConverter::toMyReviewPreviewDTO)
                        .toList();

        return StoreResDTO.MyReviewPageDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static StoreResDTO.CreateReviewResultDTO toCreateReviewResultDTO(StoreReview review) {
        return StoreResDTO.CreateReviewResultDTO.builder()
                .storeReviewId(review.getStoreReviewId())
                .storeId(review.getStore().getStoreId())
                .myMissionId(review.getMyMission().getMyMissionId())
                .storeReviewRating(review.getStoreReviewRating())
                .storeReviewContent(review.getStoreReviewContent())
                .build();
    }
}