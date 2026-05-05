package com.example.umc10th.domain.user.controller;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")



public class UserController {
    @PostMapping("/auth/signup")
    public ApiResponse<UserResDTO.SignupResultDTO> signup(
            @RequestBody UserReqDTO.SignupDTO request
    ) {
        UserResDTO.SignupResultDTO response = UserResDTO.SignupResultDTO.builder()
                .userId(1L)
                .userLoginId(request.getUserLoginId())
                .userNickname(request.getUserNickname())
                .message("회원가입이 완료되었습니다.")
                .build();

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/user/mypage")
    public ApiResponse<UserResDTO.MyPageDTO> getMyPage(
            @RequestHeader("Authorization") String authorization
    ) {
        UserResDTO.MyPageDTO response = UserResDTO.MyPageDTO.builder()
                .userNickname("nickname012")
                .userEmail("test@naver.com")
                .userPhone("010-1234-5678")
                .userPoint(2500)
                .build();

        return ApiResponse.onSuccess(response);
    }
}
