package com.ensa.agile.infrastructure.security.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ensa.agile.domain.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JwtService {
    @Value("${security.jwt.expiration-hours}")
    private Long JWT_EXPIRATION_DATE;

    @Value("${security.jwt.secret.key}")
    private String JWT_SECRET_KEY;

    private String buildToken(Map<String, Object> extraClaims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_DATE * 3600_000L);
        try {
            return Jwts.builder()
                    .claims(extraClaims)
                    .subject(subject)
                    .issuedAt(now)
                    .expiration(expiryDate)
                    .signWith(getSigningKey())
                    .compact();
        } catch (Exception e) {
            throw new JwtException("Authentication Failed ", e);
        }
    }

    public String generateToken(User user) {
        return buildToken(new HashMap<>(), user.getEmail());
    }

    public String generateToken(String email) {
        return buildToken(new HashMap<>(), email);
    }

    public String generateToken(Map<String, Object> extraClaims, User user) {
        return buildToken(extraClaims, user.getEmail());
    }

    public String generateToken(Map<String, Object> extraClaims, String email) {
        return buildToken(extraClaims, email);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, User user) {
        return extractEmail(token).equals(user.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
