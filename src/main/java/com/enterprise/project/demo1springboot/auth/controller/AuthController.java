package com.enterprise.project.demo1springboot.auth.controller;

import com.enterprise.project.demo1springboot.auth.model.request.LoginRequest;
import com.enterprise.project.demo1springboot.auth.model.request.RegisterRequest;
import com.enterprise.project.demo1springboot.auth.model.response.TokenResponse;
import com.enterprise.project.demo1springboot.auth.service.IAuthService;
import com.enterprise.project.demo1springboot.shared.model.response.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody RegisterRequest registerRequest) {
        TokenResponse tokenResponse = iAuthService.register(registerRequest);

        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setMessage("Token creado!");
        standardResponse.setData(tokenResponse);
        standardResponse.setStatusCode(HttpStatus.CREATED.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(standardResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = iAuthService.auth(loginRequest);

        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setMessage("Inicio de sesión exitoso");
        standardResponse.setData(tokenResponse);
        standardResponse.setStatusCode(HttpStatus.OK.toString());

        return ResponseEntity.status(HttpStatus.OK).body(standardResponse);
    }

}
