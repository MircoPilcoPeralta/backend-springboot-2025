package com.enterprise.project.demo1springboot.auth.service;

import com.enterprise.project.demo1springboot.auth.model.entity.User;

import java.util.Date;

public interface IJwtService {

    /**
     * todo agregar documentación de métodos
     */
    String generateToken(final User user);

    String generateRefreshToken(final User user);

    String extractUsername(String jwt);

    Date extractExpirationDate(String jwt);

    Boolean isTokenExpired(String token);

    Boolean isTokenValid(String jwt, User user);



}
