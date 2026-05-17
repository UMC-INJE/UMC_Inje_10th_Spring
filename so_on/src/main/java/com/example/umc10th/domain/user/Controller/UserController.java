package com.example.umc10th.domain.user.Controller;

import com.example.umc10th.domain.user.Service.UserService;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.JoinResultDto> join(@Valid @RequestBody UserRequestDTO.JoinDto request) {
        // 임시 응답 (Stub)
        return ApiResponse.onSuccess(UserResponseDTO.JoinResultDto.builder()
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .build());
    }

    @Operation(summary = "사용자 홈 조회", description = "홈 화면의 위치, 포인트, 미션 진행 현황과 진행 중 미션 목록을 조회합니다.")
    @GetMapping("/{userId}/home")
    public ApiResponse<UserResponseDTO.HomeDto> getHome(
            @PathVariable Long userId,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "1") Integer page) {
        return ApiResponse.onSuccess(userService.getHome(userId, location, page));
    }

    @Operation(summary = "마이페이지 조회", description = "마이페이지 화면의 프로필, 연락처, 포인트 정보를 조회합니다.")
    @GetMapping("/{userId}/my-page")
    public ApiResponse<UserResponseDTO.MyPageDto> getMyPage(@PathVariable Long userId) {
        return ApiResponse.onSuccess(userService.getMyPage(userId));
    }
}
