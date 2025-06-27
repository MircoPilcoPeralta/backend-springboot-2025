package com.enterprise.project.demo1springboot.auth.service;

import com.enterprise.project.demo1springboot.auth.model.entity.Token;
import com.enterprise.project.demo1springboot.auth.model.entity.User;
import com.enterprise.project.demo1springboot.auth.model.repository.ITokenRepository;
import com.enterprise.project.demo1springboot.auth.model.repository.IUserRepository;
import com.enterprise.project.demo1springboot.auth.model.request.LoginRequest;
import com.enterprise.project.demo1springboot.auth.model.request.RegisterRequest;
import com.enterprise.project.demo1springboot.auth.model.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository iUserRepository;
    private final ITokenRepository iTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService iJwtService;

    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.email())
                .name(registerRequest.name())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();
        // creamos el usuario en bd
        User savedUser = iUserRepository.save(user);

        // Crear token y refresh token
        String jwt = iJwtService.generateToken(savedUser);
        String refreshToken = iJwtService.generateRefreshToken(savedUser);

        // solo para guardar los tokens en base de datos
        Token token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        iTokenRepository.save(token);
        // solo para guardar los tokens en base de datos

        return new TokenResponse(jwt, refreshToken);
    }

    @Override
    public TokenResponse auth(LoginRequest loginRequest) {
        return null;
    }
}
