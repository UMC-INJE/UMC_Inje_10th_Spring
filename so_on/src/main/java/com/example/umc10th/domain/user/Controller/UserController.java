package com.example.umc10th.domain.user.Controller;

import com.example.umc10th.domain.user.Service.UserService;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "이메일과 비밀번호를 받아 회원을 생성하고 DB에 저장합니다.")
    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.SignupResultDto> join(@Valid @RequestBody UserRequestDTO.SignupDto request) {
        return ApiResponse.onSuccess(userService.signup(request));
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호를 검증한 뒤 JWT 토큰을 발급합니다.")
    @PostMapping("/login")
    public ApiResponse<UserResponseDTO.LoginResultDto> login(@Valid @RequestBody UserRequestDTO.LoginDto request) {
        return ApiResponse.onSuccess(userService.login(request));
    }

    @Operation(summary = "사용자 홈 조회", description = "홈 화면의 위치, 포인트, 미션 진행 현황과 진행 중 미션 목록을 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{userId}/home")
    public ApiResponse<UserResponseDTO.HomeDto> getHome(
            @PathVariable Long userId,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "1") Integer page) {
        return ApiResponse.onSuccess(userService.getHome(userId, location, page));
    }

    @Operation(summary = "마이페이지 조회", description = "마이페이지 화면의 프로필, 연락처, 포인트 정보를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/my-page")
    public ApiResponse<UserResponseDTO.MyPageDto> getMyPage(
            @Valid @RequestBody UserRequestDTO.MyPageRequestDto request,
            @AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.onSuccess(userService.getMyPage(request.getUserId(), principal));
    }
}
