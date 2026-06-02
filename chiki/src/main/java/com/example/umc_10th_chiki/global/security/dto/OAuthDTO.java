package com.example.umc_10th_chiki.global.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthDTO {
    private String id;
    private String email;
    private String name;
}