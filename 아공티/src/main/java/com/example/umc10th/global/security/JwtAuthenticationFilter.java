package com.example.umc10th.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. 요청 헤더에서 Authorization 꺼내기
        String authorization = request.getHeader("Authorization");

        // 2. Bearer 토큰 형식이 맞는지 확인
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7); // "Bearer " 뒤의 토큰 문자열만 쏙 빼기

            // 3. 토큰이 진짜라면 시큐리티 통행증 발급
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmail(token);

                // 스프링 시큐리티 전용 인증 도장 조립
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

                // 시큐리티 세션 장부에 강제로 통과 도장 쾅!
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 검문소(필터)로 넘어가기
        filterChain.doFilter(request, response);
    }
}