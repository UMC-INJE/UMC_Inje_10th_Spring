package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.mission.entity.MyMission;
import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.entity.StoreReview;
import com.example.umc10th.domain.user.entity.User;

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