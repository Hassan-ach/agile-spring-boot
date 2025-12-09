package com.ensa.agile.infrastructure.security.exception;

import com.ensa.agile.application.user.exception.AuthenticationFailureException;

public class JwtValidationException extends AuthenticationFailureException {
    public JwtValidationException(String msg) { super(msg); }

    public JwtValidationException() { super("Token Validation Error"); }
}
