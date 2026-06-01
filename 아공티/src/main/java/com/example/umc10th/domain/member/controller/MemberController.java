package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    // 내 정보 조회
    @GetMapping("/me/{memberId}")
    @Operation(summary = "내 정보 조회 API", security = @SecurityRequirement(name = "BearerAuth"))
    public ApiResponse<MemberResDTO.GetInfo> getMyInfo(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberService.getInfo(memberId));
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/me/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", security = @SecurityRequirement(name = "BearerAuth")) // 🌟 자물쇠 활성화!
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<Review> reviewPage = memberService.getMyReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewPage));
    }
    //회원가입
    @PostMapping("/register")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @Valid @RequestBody MemberReqDTO.JoinDTO request
    ) {
        Member member = memberService.joinMember(request);

        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    //로그인
    @PostMapping("/login")
    public ApiResponse<String> login(
            @Valid @RequestBody MemberReqDTO.LoginDTO request
    ) {
        // 서비스에서 비번 대조 검증을 하고 유저 객체를 가져옵니다.
        Member member = memberService.login(request);
        // 검증 성공 시 이메일을 기반으로 JWT 토큰 생성
        String token = jwtUtil.createToken(member.getEmail());
        // 토큰 전달
        return ApiResponse.onSuccess(token);
    }
}