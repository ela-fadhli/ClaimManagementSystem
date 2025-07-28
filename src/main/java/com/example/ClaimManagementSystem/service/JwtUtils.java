package com.example.ClaimManagementSystem.service;

import org.springframework.security.core.Authentication;

public interface JwtUtils {

    String generateToken(Authentication authentication);
    boolean validateToken(String jwt);
    String getUsernameFromToken(String jwt);
}