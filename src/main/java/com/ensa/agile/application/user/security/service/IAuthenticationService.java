package com.ensa.agile.application.user.security.service;

import com.ensa.agile.application.user.exception.InvalidCredentialsException;

public interface IAuthenticationService {
    String login(String email, String password) throws InvalidCredentialsException;

    String generateToken(String email);
}
