package com.example.umc_10th_chiki.global.security.service;

import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.repository.MemberRepository;
import com.example.umc_10th_chiki.global.security.OAuthMember;
import com.example.umc_10th_chiki.global.security.dto.KakaoDTO;
import com.example.umc_10th_chiki.global.security.dto.OAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuthMember = super.loadUser(userRequest);

        String providerId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        String socialUid = String.valueOf((Long) oAuthMember.getAttribute("id"));

        Map<String, Object> attributes = oAuthMember.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");

        OAuthDTO dto;
        if ("KAKAO".equals(providerId)) {
            String email = attributes.get("email").toString();
            String name = profile.get("nickname").toString();
            dto = new KakaoDTO(socialUid, email, name);
        } else {
            throw new RuntimeException("지원하지 않는 소셜 로그인입니다.");
        }

        // DB 확인 후 없으면 저장
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseGet(() -> {
                    Member newMember = Member.builder()
                            .email(dto.getEmail())
                            .name(dto.getName())
                            // OAuth 사용자는 비밀번호가 불필요하므로 임의값 저장
                            .password("OAUTH_USER_NO_PASSWORD")
                            .build();
                    return memberRepository.save(newMember);
                });

        return new OAuthMember(member, oAuthMember.getAttributes());
    }
}