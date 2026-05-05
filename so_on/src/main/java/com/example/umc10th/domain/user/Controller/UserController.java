package com.example.umc10th.domain.user.Controller;

import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.JoinResultDto> join(@RequestBody UserRequestDTO.JoinDto request) {
        // 임시 응답 (Stub)
        return ApiResponse.onSuccess(UserResponseDTO.JoinResultDto.builder()
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{userId}/home")
    public ApiResponse<UserResponseDTO.HomeDto> getHome(
            @PathVariable Long userId,
            @RequestParam String location) {
        return ApiResponse.onSuccess(UserResponseDTO.HomeDto.builder()
                .userName("소온")
                .location(location)
                .totalPoints(500)
                .build());
    }
}