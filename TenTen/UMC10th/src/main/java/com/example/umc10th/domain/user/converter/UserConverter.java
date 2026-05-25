package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    //8. 메서드 추가
    public static User toUser(
            UserReqDTO.SignUpDTO request,
            Region region,
            String encodedPassword
    ) {
        return User.builder()
                .region(region)
                .userLoginId(request.getUserLoginId())
                .userPassword(encodedPassword)
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userGender(request.getUserGender())
                .userBirthdate(request.getUserBirthdate())
                .userAddress(request.getUserAddress())
                .userPhone(request.getUserPhone())
                .userNickname(request.getUserNickname())
                .userPoint(0)
                .status("ACTIVE")
                .build();
    }

    public static UserResDTO.SignUpResultDTO toSignUpResultDTO(User user) {
        return UserResDTO.SignUpResultDTO.builder()
                .userId(user.getUserId())
                .userLoginId(user.getUserLoginId())
                .userNickname(user.getUserNickname())
                .message("회원가입에 성공하였습니다.")
                .build();
    }

    public static UserResDTO.MyPageDTO toMyPageDTO(User user) {
        return UserResDTO.MyPageDTO.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .userPhone(user.getUserPhone())
                .userPoint(user.getUserPoint())
                .build();
    }
}