package com.enterprise.project.demo1springboot.auth.service;

import com.enterprise.project.demo1springboot.auth.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements IJwtService {

    @Value("${aplication.security.jwt.secret-key}")
    private String secretKey;

    @Value("${aplication.security.jwt.expiration}")
    private Long jwtExp;

    @Value("${aplication.security.jwt.refresh-token-expiration}")
    private Long refreshTokenExp;


    @Override
    public String generateToken(User user) {
        return buildToken(user, jwtExp);
    }

    @Override
    public String generateRefreshToken(User user) {
        return buildToken(user, refreshTokenExp);
    }

    @Override
    public String extractUsername(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    @Override
    public Date extractExpirationDate(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getExpiration();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }


    @Override
    public Boolean isTokenValid(String jwt, User user) {
        final String email = extractUsername(jwt);
        return (email.equals(user.getEmail()) && !isTokenExpired(jwt));
    }

    private String buildToken(User user, Long jwtExp) {
        Map<String, String> payload = Map.of("name", user.getName());

        return Jwts.builder()
                .setId(user.getId().toString())
                .setClaims(payload)
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis()  + jwtExp))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
