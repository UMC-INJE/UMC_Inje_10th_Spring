package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.mission.entity.MyMission;
import com.example.umc10th.domain.mission.repository.MyMissionRepository;
import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.entity.StoreReview;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.store.repository.StoreReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreReviewRepository storeReviewRepository;
    private final MyMissionRepository myMissionRepository;
    private final UserRepository userRepository;
    public StoreResDTO.MyReviewPageDTO getMyReviewList(
            Long userId,
            Integer page,
            Integer size
    ){

        PageRequest pageRequest =
                PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<StoreReview> reviewPage =
                storeReviewRepository.findAllByUser_UserId(
                        userId,
                        pageRequest
                );

        return StoreConverter.toMyReviewPageDTO(reviewPage);
    }
    // 리뷰 작성 쿼리
    @Transactional
    public StoreResDTO.CreateReviewResultDTO createReview(
            Long userId,
            Long storeId,
            StoreReqDTO.CreateReviewDTO request
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        MyMission myMission = myMissionRepository.findById(request.getMyMissionId())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        if (!myMission.getUser().getUserId().equals(userId)) {
            throw new ProjectException(GeneralErrorCode.FORBIDDEN);
        }

        if (!myMission.getMission().getStore().getStoreId().equals(storeId)) {
            throw new ProjectException(GeneralErrorCode.BAD_REQUEST);
        }

        if (!myMission.getStatus().equals("진행완료")) {
            throw new ProjectException(GeneralErrorCode.BAD_REQUEST);
        }

        StoreReview review = StoreConverter.toStoreReview(store, user, myMission, request);
        StoreReview savedReview = storeReviewRepository.save(review);

        return StoreConverter.toCreateReviewResultDTO(savedReview);
    }
}