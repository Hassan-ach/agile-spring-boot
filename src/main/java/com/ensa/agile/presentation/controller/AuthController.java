package com.ensa.agile.presentation.controller;

import com.ensa.agile.application.user.request.AuthenticationRequest;
import com.ensa.agile.application.user.request.RegisterRequest;
import com.ensa.agile.application.user.response.AuthenticationResponse;
import com.ensa.agile.application.user.usecase.LoginUseCase;
import com.ensa.agile.application.user.usecase.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request) {

        AuthenticationResponse response =
            registerUseCase.executeTransactionally(
                new RegisterRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>
    login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response =
            loginUseCase.execute(new AuthenticationRequest(request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/vip")
    public ResponseEntity<String> vipAccess() {
        return ResponseEntity.ok("You have accessed a VIP endpoint!");
    }
}
