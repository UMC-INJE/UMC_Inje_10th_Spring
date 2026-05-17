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
import com.example.umc10th.domain.user.Repository.UserRepository;
import com.example.umc10th.domain.user.code.UserErrorCode;
import com.example.umc10th.domain.user.exception.UserException;
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
    private final UserRepository userRepository;

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

    // 커서 기반 페이지네이션 - 내가 작성한 리뷰 조회
    public ReviewResponseDTO.MemberReviewPageDto getMemberReviews(Long userId, ReviewRequestDTO.MemberReviewRequestDto request) {
        // 사용자 존재 확인
        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        String query = request.getQuery().toLowerCase();
        if (!query.equals("id") && !query.equals("star")) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_REQUEST);
        }

        Pageable pageable = PageRequest.of(0, request.getPageSize() + 1);
        List<Review> reviews;

        if (query.equals("id")) {
            // ID 순 정렬 (내림차순)
            reviews = reviewRepository.findMemberReviewsById(userId, request.getCursor(), pageable);
        } else {
            // 별점 순 정렬 (내림차순)
            Float cursor = request.getCursor() == null ? null : request.getCursor().floatValue();
            Long lastId = request.getCursor() == null ? null : Long.MAX_VALUE;
            reviews = reviewRepository.findMemberReviewsByStar(userId, cursor, lastId, pageable);
        }

        // hasNext 판단 (pageSize + 1개를 조회했으므로)
        boolean hasNext = reviews.size() > request.getPageSize();
        if (hasNext) {
            reviews = reviews.subList(0, request.getPageSize());
        }

        // 다음 커서 값 계산
        Long nextCursor = null;
        if (hasNext && !reviews.isEmpty()) {
            Review lastReview = reviews.get(reviews.size() - 1);
            if (query.equals("id")) {
                nextCursor = lastReview.getId();
            } else {
                nextCursor = lastReview.getStar().longValue();
            }
        }

        List<ReviewResponseDTO.MemberReviewDto> reviewList = reviews.stream()
                .map(this::toMemberReviewDto)
                .toList();

        return ReviewResponseDTO.MemberReviewPageDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    private ReviewResponseDTO.MemberReviewDto toMemberReviewDto(Review review) {
        return ReviewResponseDTO.MemberReviewDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt() == null ? null : review.getCreatedAt().toLocalDate().toString())
                .build();
    }
}
