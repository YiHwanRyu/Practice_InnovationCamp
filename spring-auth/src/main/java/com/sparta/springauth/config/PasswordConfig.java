package com.sparta.springauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { // 인터페이스
        return new BCryptPasswordEncoder(); // 구현체, BCrypt 비밀번호를 암호화하는 함수(해쉬함수)
    }
}