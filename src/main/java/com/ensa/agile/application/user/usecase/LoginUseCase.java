package com.ensa.agile.application.user.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.user.mapper.AuthenticationResponseMapper;
import com.ensa.agile.application.user.request.AuthenticationRequest;
import com.ensa.agile.application.user.response.AuthenticationResponse;
import com.ensa.agile.application.user.security.IAuthenticationService;

@Component
public class LoginUseCase
    extends BaseUseCase<AuthenticationRequest, AuthenticationResponse> {
    private final IAuthenticationService autenticationService;

    public LoginUseCase(IAuthenticationService autenticationService,
                        ITransactionalWrapper transactionalWrapper) {
        super(transactionalWrapper);
        this.autenticationService = autenticationService;
    }

    public AuthenticationResponse execute(AuthenticationRequest request) {
        String token = autenticationService.login(request.getEmail(),
                                                  request.getPassword());
        return AuthenticationResponseMapper.toResponse(token);
    }
}
