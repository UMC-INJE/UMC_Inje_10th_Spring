package com.example.umc_10th_chiki.domain.member.service;

import com.example.umc_10th_chiki.domain.member.dto.MemberReqDTO;
import com.example.umc_10th_chiki.domain.member.entity.Member;
import com.example.umc_10th_chiki.domain.member.repository.MemberRepository;
import com.example.umc_10th_chiki.domain.store.entity.Region;
import com.example.umc_10th_chiki.domain.store.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    public Member join(MemberReqDTO.JoinDTO request) {

        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new RuntimeException("해당 지역을 찾을 수 없습니다."));

        Member newMember = Member.builder()
                .name(request.userName())
                .alias(request.userAlias())
                .birthDate(request.birthDate())
                .email(request.userEmail())
                .address(request.userAddress())
                .gender(request.userGender())
                .phone(request.userPhone())
                .status("ACTIVE")
                .notifySettingId(request.notifySettingId() != null ? request.notifySettingId() : 1L)
                .region(region)
                .build();

        return memberRepository.save(newMember);
    }
}