package com.example.umc_10th_chiki.global.config;

import com.example.umc_10th_chiki.global.apiPayload.handler.CustomAccessDeniedHandler;
import com.example.umc_10th_chiki.global.apiPayload.handler.CustomAuthenticationEntryPoint;
import com.example.umc_10th_chiki.global.security.filter.JwtAuthFilter;
import com.example.umc_10th_chiki.global.security.handler.OAuthSuccessHandler;
import com.example.umc_10th_chiki.global.security.service.CustomOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // [8주차]: 예외 처리 핸들러 유지
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // [9주차]: JWT 및 OAuth 관련 의존성 주입 추가
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomOAuthService customOAuthService;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    // 로그인 없이 접근 가능한 Public API 목록
    private final String[] allowUris = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/members/signup",
            "/members/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable) // REST API이므로 기본 인증 비활성화

                // [9주차]: 기존 폼 로그인 비활성화 및 세션 상태를 Stateless(무상태)로 변경
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Public / Private API 접근 권한 분리 인가 설정 (유지)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )

                // 인증/인가 실패 시 예외 처리 통일 (유지)
                // (JWT 필터를 무사히 통과했으나 권한이 없는 경우 등을 여기서 안전하게 잡아준다.)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )

                // [9주차]: OAuth2 소셜 로그인 설정 추가
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService) // 카카오 등에서 사용자 정보를 가져온 후 실행할 로직
                        )
                        .successHandler(oAuthSuccessHandler) // 소셜 로그인 성공 시 JWT를 발급할 핸들러 연결
                )

                // [9주차]: 커스텀 JWT 필터 등록
                // (스프링 시큐리티의 기본 인증 필터 앞에 우리가 만든 JWT 검증 필터를 끼워 넣음)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 비밀번호 솔트 처리를 위한 BCrypt 인코더 유지
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}