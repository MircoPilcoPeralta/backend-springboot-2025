package com.enterprise.project.demo1springboot.auth.service;

import com.enterprise.project.demo1springboot.auth.model.entity.Token;
import com.enterprise.project.demo1springboot.auth.model.entity.User;
import com.enterprise.project.demo1springboot.auth.model.repository.ITokenRepository;
import com.enterprise.project.demo1springboot.auth.model.repository.IUserRepository;
import com.enterprise.project.demo1springboot.auth.model.request.LoginRequest;
import com.enterprise.project.demo1springboot.auth.model.request.RegisterRequest;
import com.enterprise.project.demo1springboot.auth.model.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository iUserRepository;
    private final ITokenRepository iTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService iJwtService;

    private final AuthenticationManager authenticationManager;

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
        // guardar una sesión en el security context holder
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        // obtener los datos del usuario
        User user = iUserRepository.findUserByEmail(loginRequest.email()).orElseThrow(() -> new RuntimeException("No se obtuvo el usuario") );
        // generar un token
        String jwt = iJwtService.generateToken(user);
        String refreshToken = iJwtService.generateRefreshToken(user);

        // solo para guardar los tokens en base de datos
        Token token = Token.builder()
                .user(user)
                .token(jwt)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        iTokenRepository.save(token);
        // devolvemos
        return new TokenResponse(jwt, refreshToken);
    }

}
