package com.example.umc10th.global.config;

import com.example.umc10th.global.security.CustomAccessDeniedHandler;
import com.example.umc10th.global.security.CustomAuthenticationEntryPoint;
import com.example.umc10th.global.security.CustomAuthenticationFailureHandler;
import com.example.umc10th.global.security.CustomAuthenticationSuccessHandler;
import com.example.umc10th.global.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //기본 보안 장치 해제 (spring Security가 기본으로 켜두는 옛날 방식의 보안 장치들을 끄는(disable) 설정)
                //화면(HTML)을 직접 리턴하는 옛날 웹사이트 <-> 데이터를 주고 받는 형식의 REST API 방식에는 불필요!
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                // [인가 검사 규칙] ⭐️
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/signup", "/login").permitAll() // 회원가입 부분은 all 허용!
                        .anyRequest().authenticated()  // ⭐️ 그외의 모든 경로는 인증 필요함!
                )
                // [폼 로그인 창]
                // ️폼 로그인에서 email, password를 쓰도록 함
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)// 각 성공/실패 시 알림창, 에러 메시지 표시
                )
                //  [예외 처리]

                .exceptionHandling(exception -> exception
                        //*customAuthenticationEntryPoint
                        //  로그인 안한 사람이 로그인 한 사람만 볼 수 있는 웹으로 억지로 들어가려고 할 때..  어떻게 할지...
                        //  (예: "401 에러와 함께 로그인 페이지로 가라고 안내해라")
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        // *customAccessDeniedHandler
                        // 로그인은 했지만 권한 없는 페이지 요청을 보낼 때 어떻게 처리할지..
                        // (예: "403 권한 없음 에러를 출력해라")
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                // 실제 신원확인 해주는 부분 **authenticationProvider** -> 워크북 내용!
                .authenticationProvider(authenticationProvider());

        return http.build();
    }


    @Bean // 의존성 주입 (DI; Dependency Injection)
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // '스프링 컨테이너'는 생성된 객체들을 안전하게 모아두고 관리하는 거대한 보관소.
    // 이 컨테이너 안에 들어가는 객체 하나하나를 Bean이라고 함.
    //메서드 위에 @Bean을 적어두면, 스프링 프로젝트가 처음 실행(부팅)될 때 해당 메서드를 딱 한 번 실행! -> 여기서는 부팅시 암호화된 비밀번호(결과물)이 저장되는게 아니라, 암호화된 비밀번호를 만들수 있는 "빈 기계"가 생성되는 것!!!!
    //즉 아래에서는 <passwordEncoder>라는 이름표로 리턴값을(BCrypt화된 비밀번호) 저장하는 것
    @Bean  //⭐️pw를 Bcrypt 방식으로 암호화해서 저장할 수 있게 구현한 부분 !!!
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  // PasswordEncoder 인터페이스를 쓰고, 실제 구현체는 BCryptPasswordEncoder


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
