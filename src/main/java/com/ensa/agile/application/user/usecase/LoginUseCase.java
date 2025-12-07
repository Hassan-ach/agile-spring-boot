package com.ensa.agile.application.user.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.user.mapper.AuthenticationResponseMapper;
import com.ensa.agile.application.user.request.AuthenticationRequest;
import com.ensa.agile.application.user.response.AuthenticationResponse;
import com.ensa.agile.application.user.security.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginUseCase implements BaseUseCase<AuthenticationRequest, AuthenticationResponse> {
    private final IAuthenticationService autenticationService;

    public AuthenticationResponse execute(AuthenticationRequest request) {
        String token = autenticationService.login(request.getEmail(), request.getPassword());
        return AuthenticationResponseMapper.toResponse(token);
    }

}
