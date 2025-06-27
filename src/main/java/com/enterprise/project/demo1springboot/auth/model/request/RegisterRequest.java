package com.enterprise.project.demo1springboot.auth.model.request;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
