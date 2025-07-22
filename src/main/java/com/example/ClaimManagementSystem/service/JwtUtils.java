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

@Component
public class JwtUtils {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public JwtUtils(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationInMillis()))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String jwt) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}