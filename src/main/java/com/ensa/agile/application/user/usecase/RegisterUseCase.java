package com.ensa.agile.application.user.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.user.mapper.AuthenticationResponseMapper;
import com.ensa.agile.application.user.mapper.RegisterRequestMapper;
import com.ensa.agile.application.user.request.RegisterRequest;
import com.ensa.agile.application.user.response.AuthenticationResponse;
import com.ensa.agile.application.user.security.IAuthenticationService;
import com.ensa.agile.application.user.security.IPasswordEncoder;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.exception.ValidationException;
import com.ensa.agile.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegisterUseCase implements BaseUseCase<RegisterRequest, AuthenticationResponse> {
    private final UserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;
    private final IAuthenticationService authenticationService;

    public AuthenticationResponse execute(RegisterRequest registerRequest) {
        if (userRepository.existsByEmailIgnoreCase(registerRequest.getEmail())) {
            throw new ValidationException("Email already used");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User u = RegisterRequestMapper.toUser(registerRequest, hashedPassword);

        userRepository.save(u);

        return AuthenticationResponseMapper.toResponse(
                authenticationService.generateToken(u.getEmail()));
    }

}
