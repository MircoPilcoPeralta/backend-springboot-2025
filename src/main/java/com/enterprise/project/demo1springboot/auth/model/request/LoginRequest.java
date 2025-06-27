package com.enterprise.project.demo1springboot.auth.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
