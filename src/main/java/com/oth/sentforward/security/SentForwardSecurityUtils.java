package com.oth.sentforward.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class SentForwardSecurityUtils {

    private String salt = "OtH-R€g€nsbUrg-S€ntForwArD";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10, new SecureRandom(salt.getBytes()));

    };


}
