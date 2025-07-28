package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.config.JwtConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.ErrorManager;

public interface JwtUtils {

    String generateToken(Authentication authentication);
    boolean validateToken(String jwt);
    String getUsernameFromToken(String jwt);
}