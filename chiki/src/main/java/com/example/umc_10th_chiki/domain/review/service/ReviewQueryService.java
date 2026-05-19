package com.example.umc_10th_chiki.domain.review.service;

import com.example.umc_10th_chiki.domain.review.entity.Review;
import com.example.umc_10th_chiki.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 전용이므로 성능 최적화를 위해 달아줍니다.
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Slice<Review> getMyReviews(Long memberId, String cursor, int size) {
        // 커서 페이징은 limit만 필요하므로 페이지 번호는 항상 0으로 요청합니다.
        PageRequest pageRequest = PageRequest.of(0, size);

        // 1. 커서가 없는 경우 (처음 요청 시)
        if (cursor == null || cursor.equals("-1")) {
            return reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageRequest);
        }

        // 2. 커서 파싱 ("score:4.5_12" 또는 "id:15" 형태)
        String[] parts = cursor.split(":");
        String sortType = parts[0];
        String cursorValue = parts[1];

        // 3. 별점(score) 기준 페이징일 경우
        if ("score".equals(sortType)) {
            String[] scoreAndId = cursorValue.split("_");
            Float score = Float.parseFloat(scoreAndId[0]);
            Long id = Long.parseLong(scoreAndId[1]);
            return reviewRepository.findByMemberIdAndCursorScore(memberId, score, id, pageRequest);
        }
        // 4. 최신순(ID) 기준 페이징일 경우
        else {
            Long id = Long.parseLong(cursorValue);
            return reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, id, pageRequest);
        }
    }
}