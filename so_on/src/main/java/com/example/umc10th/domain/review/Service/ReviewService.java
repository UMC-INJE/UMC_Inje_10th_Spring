package com.example.umc10th.domain.review.Service;

import com.example.umc10th.domain.owner.Repository.OwnerRepository;
import com.example.umc10th.domain.review.Entity.Reply;
import com.example.umc10th.domain.review.Entity.Review;
import com.example.umc10th.domain.review.Repository.ReplyRepository;
import com.example.umc10th.domain.review.Repository.ReviewRepository;
import com.example.umc10th.domain.review.code.ReviewErrorCode;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private static final int REVIEW_PAGE_SIZE = 10;

    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;
    private final StoreRepository storeRepository;
    private final OwnerRepository ownerRepository;

    public ReviewResponseDTO.StoreReviewPageDto getStoreReviews(Long ownerId, Long storeId, Integer page) {
        validateOwnerStore(ownerId, storeId);

        int pageNumber = page == null || page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, REVIEW_PAGE_SIZE);

        Page<Review> reviews = reviewRepository.findStoreReviews(ownerId, storeId, pageable);
        List<ReviewResponseDTO.StoreReviewDto> reviewList = reviews.getContent().stream()
                .map(review -> {
                    Reply reply = replyRepository.findFirstByReviewIdOrderByIdDesc(review.getId()).orElse(null);
                    return toStoreReviewDto(review, reply);
                })
                .toList();

        return ReviewResponseDTO.StoreReviewPageDto.builder()
                .storeId(storeId)
                .listSize(reviewList.size())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .currentPage(reviews.getNumber() + 1)
                .isLast(reviews.isLast())
                .reviewList(reviewList)
                .build();
    }

    @Transactional
    public ReviewResponseDTO.ReplyResultDto createReply(Long ownerId, Long reviewId, ReviewRequestDTO.ReplyCreateDto request) {
        if (request == null || request.getContent() == null || request.getContent().isBlank()) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_REQUEST);
        }

        Review review = reviewRepository.findReviewDetail(ownerId, reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_REVIEW_NOT_FOUND));

        if (replyRepository.existsByReviewId(reviewId)) {
            throw new ReviewException(ReviewErrorCode.REPLY_ALREADY_EXISTS);
        }

        var owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.OWNER_STORE_NOT_FOUND));

        Reply reply = replyRepository.save(Reply.builder()
                .content(request.getContent())
                .review(review)
                .owner(owner)
                .build());

        return ReviewResponseDTO.ReplyResultDto.builder()
                .reviewId(reviewId)
                .replyId(reply.getId())
                .content(reply.getContent())
                .build();
    }

    private void validateOwnerStore(Long ownerId, Long storeId) {
        if (ownerId == null || storeId == null || !storeRepository.existsOwnerStore(ownerId, storeId)) {
            throw new ReviewException(ReviewErrorCode.OWNER_STORE_NOT_FOUND);
        }
    }

    private ReviewResponseDTO.StoreReviewDto toStoreReviewDto(Review review, Reply reply) {
        return ReviewResponseDTO.StoreReviewDto.builder()
                .reviewId(review.getId())
                .reviewerNickname(review.getUser().getName())
                .score(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt() == null ? null : review.getCreatedAt().toLocalDate().toString())
                .photoUrls(parsePhotoUrls(review.getPhotoUrls()))
                .reply(reply == null ? null : ReviewResponseDTO.ReplyDto.builder()
                        .replyId(reply.getId())
                        .content(reply.getContent())
                        .createdAt(reply.getCreatedAt() == null ? null : reply.getCreatedAt().toLocalDate().toString())
                        .build())
                .build();
    }

    private List<String> parsePhotoUrls(String photoUrls) {
        if (photoUrls == null || photoUrls.isBlank()) {
            return List.of();
        }

        return Arrays.stream(photoUrls.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .toList();
    }
}
