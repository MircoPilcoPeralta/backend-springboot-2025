package com.enterprise.project.demo1springboot.auth.service;


import com.enterprise.project.demo1springboot.auth.model.request.LoginRequest;
import com.enterprise.project.demo1springboot.auth.model.request.RegisterRequest;
import com.enterprise.project.demo1springboot.auth.model.response.TokenResponse;

public interface IAuthService {

    /**
     * Method to create a new user account
     * @param registerRequest new user's data
     * @return TokenResponse, which contains the token and refresh token from the user
     * */
    TokenResponse register(RegisterRequest registerRequest);

    TokenResponse auth(LoginRequest loginRequest);
}
