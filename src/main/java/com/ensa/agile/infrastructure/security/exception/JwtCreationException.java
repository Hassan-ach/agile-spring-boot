package com.ensa.agile.infrastructure.security.exception;

import com.ensa.agile.application.user.exception.AuthenticationFailureException;

public class JwtCreationException extends AuthenticationFailureException {
    public JwtCreationException(String msg) { super(msg); }

    public JwtCreationException() { super("Autentication Error"); }
}
