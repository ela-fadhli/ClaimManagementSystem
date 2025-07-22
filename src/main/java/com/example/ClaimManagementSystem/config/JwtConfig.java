package com.example.ClaimManagementSystem.config;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Long getExpirationInMillis() {
        return Long.parseLong(expiration) * 1000;
    }
}