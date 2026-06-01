package com.example.umc_10th_chiki.global.security.handler;

import com.example.umc_10th_chiki.global.apiPayload.ApiResponse;
import com.example.umc_10th_chiki.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc_10th_chiki.global.security.AuthMember;
import com.example.umc_10th_chiki.global.security.OAuthMember;
import com.example.umc_10th_chiki.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);

        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        Map<String, String> tokenData = new HashMap<>();
        tokenData.put("accessToken", accessToken);

        // 프로젝트 ApiResponse 구조에 맞게 성공 응답 생성
        ApiResponse<Map<String, String>> responseBody = ApiResponse.onSuccess(tokenData);

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}