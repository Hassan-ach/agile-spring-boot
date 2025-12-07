package com.ensa.agile.infrastructure.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ensa.agile.application.user.security.IAuthenticationService;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.exception.InvalidCredentialsException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticationService implements IAuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationProvider provider;

    @Override
    public String login(String email, String password) throws InvalidCredentialsException {
        Authentication auth = this.provider.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        if (auth == null || !auth.isAuthenticated()) {
            throw new InvalidCredentialsException();
        }
        return jwtService.generateToken((User) auth.getPrincipal());
    }

    @Override
    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

}
