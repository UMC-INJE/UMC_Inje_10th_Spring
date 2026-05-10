package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDto {
        Long userId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeDto {
        String userName;
        String location;
        Integer totalPoints;
        Integer completedMissionCount;
        Integer missionGoalCount;
        Integer rewardPoint;
        HomeMissionPageDto ongoingMissionPage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeMissionPageDto {
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Integer currentPage;
        Boolean isLast;
        List<HomeMissionDto> missionList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeMissionDto {
        Long memberMissionId;
        Long missionId;
        String storeName;
        String storeCategory;
        Integer rewardPoint;
        String deadline;
        String missionContent;
        String status;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageDto {
        Long userId;
        String nickname;
        String email;
        String phoneNumber;
        String phoneVerificationStatus;
        Integer totalPoints;
        Long reviewCount;
        String profileImageUrl;
    }
}
