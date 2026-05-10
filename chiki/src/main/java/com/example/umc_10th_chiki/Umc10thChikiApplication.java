package com.example.umc_10th_chiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // 추가

@EnableJpaAuditing
@SpringBootApplication
public class Umc10thChikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thChikiApplication.class, args);
    }
}