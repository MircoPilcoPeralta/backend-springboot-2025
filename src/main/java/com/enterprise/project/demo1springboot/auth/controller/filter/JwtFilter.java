package com.enterprise.project.demo1springboot.auth.controller.filter;

import com.enterprise.project.demo1springboot.auth.model.entity.User;
import com.enterprise.project.demo1springboot.auth.model.repository.IUserRepository;
import com.enterprise.project.demo1springboot.auth.service.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final IUserRepository iUserRepository;
    private final IJwtService iJwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ") ) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String email = iJwtService.extractUsername(jwt);

        // voy a crear un objeto de tipo authentication
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(email == null || authentication != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // usuario1@gmail.com
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // todo validar que el email del jwt sea el mismo que el ingresado en el json del request
        request.getAttribute("email");
        // usuario23@gmail.com
        final Optional<User> userEntity = iUserRepository.findUserByEmail(email);

        if(userEntity.isPresent()) {
            final boolean isTokenValid = iJwtService.isTokenValid(jwt, userEntity.get());

            if(isTokenValid) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
