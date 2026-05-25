package com.example.umc_10th_chiki.global.config;

import com.example.umc_10th_chiki.global.apiPayload.handler.CustomAccessDeniedHandler;
import com.example.umc_10th_chiki.global.apiPayload.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // [8주차 미션]: 로그인 없이 접근 가능한 Public API 목록을 명시
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

                // 1. Public / Private API 접근 권한 분리 인가 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // 허용된 주소는 인증 없이 패스
                        .anyRequest().authenticated()           // 그 외 모든 Private API는 로그인 필수
                )

                // 2. [미션 핵심]: 인증/인가 실패 시 HTML 화면 대신 ApiResponse 규격으로 응답 통일 설정
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 401 에러 가로채기
                        .accessDeniedHandler(customAccessDeniedHandler)           // 403 에러 가로채기
                )

                // 3. 기본 폼 로그인 가동
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )

                // 4. 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/users/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // 🚨 [미션 조건]: 비밀번호 솔트 처리를 위한 BCrypt 인코더 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}