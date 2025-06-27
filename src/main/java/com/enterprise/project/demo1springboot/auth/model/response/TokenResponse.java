package com.enterprise.project.demo1springboot.auth.model.response;

public record TokenResponse(
        String token,
        String refreshToken
) {
}
