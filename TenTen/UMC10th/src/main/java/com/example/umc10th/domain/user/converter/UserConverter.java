package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

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