package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @Operation(
            summary = "회원가입 API",
            description = "사용자 회원가입 API입니다."
    )
    @PostMapping("/auth/signup")
    public ApiResponse<UserResDTO.SignUpResultDTO> signUp(

            @Valid @RequestBody UserReqDTO.SignUpDTO request
    ){

        return ApiResponse.onSuccess(
                userService.signUp(request)
        );
    }

    // 마이페이지 조회 API
    @Operation(
            summary = "마이페이지 조회 API",
            description = "사용자의 마이페이지 정보를 조회합니다."
    )
    @GetMapping("/user/mypage")
    public ApiResponse<UserResDTO.MyPageDTO> getMyPage(

            @Parameter(description = "Access Token", example = "Bearer accessToken")
            @RequestHeader(value = "Authorization", required = false)
            String authorization
    ) {

        Long userId = 1L;

        return ApiResponse.onSuccess(
                userService.getMyPage(userId)
        );
    }
}