package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.repository.RegionRepository;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public UserResDTO.SignUpResultDTO signUp(
            UserReqDTO.SignUpDTO request
    ){
        // 9.이메일 중복 검사
        if(userRepository.existsByUserEmail(
                request.getUserEmail()
        )){
            throw new ProjectException(
                    GeneralErrorCode.BAD_REQUEST
            );
        }

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() ->
                        new ProjectException(GeneralErrorCode.NOT_FOUND));

        // BCrypt 암호화
        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User newUser =
                UserConverter.toUser(
                        request,
                        region,
                        encodedPassword
                );

        User savedUser = userRepository.save(newUser);

        return UserConverter.toSignUpResultDTO(savedUser);
    }

    // 마이페이지 조회
    public UserResDTO.MyPageDTO getMyPage(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ProjectException(GeneralErrorCode.NOT_FOUND));

        return UserConverter.toMyPageDTO(user);
    }

    //9.로그인 추가
    public UserResDTO.LoginResultDTO login(
            UserReqDTO.LoginDTO request
    ){

        User user = userRepository
                .findByUserEmail(request.getUserEmail())
                .orElseThrow(() ->
                        new ProjectException(
                                GeneralErrorCode.NOT_FOUND
                        ));

        // BCrypt 검증
        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getUserPassword()
        )){
            throw new ProjectException(
                    GeneralErrorCode.BAD_REQUEST
            );
        }

        String token =
                jwtUtil.createToken(user.getUserEmail());

        return UserResDTO.LoginResultDTO
                .builder()
                .accessToken(token)
                .build();
    }
    public UserResDTO.MyPageDTO
    getMyPageByEmail(String email){

        User user = userRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                                new ProjectException(
                                        GeneralErrorCode.NOT_FOUND
                                ));

        return UserConverter.toMyPageDTO(user);
    }
}