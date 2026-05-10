package com.example.umc_10th_chiki.domain.review.service;

import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.repository.MemberRepository;
import com.example.umc_10th_chiki.domain.review.entity.Review;
import com.example.umc_10th_chiki.domain.review.repository.ReviewRepository;
import com.example.umc_10th_chiki.domain.store.entity.Store;
import com.example.umc_10th_chiki.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // [6주차 미션] 리뷰 작성 로직
    public Review createReview(Long memberId, Long storeId, String body, Float score) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .body(body)
                .score(score)
                .build();

        return reviewRepository.save(review);
    }
}