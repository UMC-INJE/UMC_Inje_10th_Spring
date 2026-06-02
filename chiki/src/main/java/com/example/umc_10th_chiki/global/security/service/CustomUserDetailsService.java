package com.example.umc_10th_chiki.global.security.service;

import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.repository.MemberRepository;
import com.example.umc_10th_chiki.global.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DB에서 이메일로 유저를 찾고, 없으면 에러 발생
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 유저를 찾을 수 없습니다: " + email));

        // 찾은 유저 엔티티를 AuthMember(인증 객체)에 담아서 반환
        return new AuthMember(member);
    }
}