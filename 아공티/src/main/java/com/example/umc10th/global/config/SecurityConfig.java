package com.example.umc10th.global.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 서비스 레이어에서 주입받아 사용할 수 있도록 빈(Bean)으로 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 테스트 및 실습 편의상 CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        // 회원가입 API 주소는 Public(로그인 없이 허용)으로 세팅
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // 그 외 모든 API는 Private(로그인 필요)으로 통제
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
