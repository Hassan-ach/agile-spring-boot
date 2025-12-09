package com.ensa.agile.application.user.exception;

import com.ensa.agile.domain.global.exception.ApplicationException;

public class AuthenticationFailureException extends ApplicationException {
    public AuthenticationFailureException(String message) {
        super("Authentication failed: " + message);
    }
    public AuthenticationFailureException() { super("Authentication failed"); }
}
