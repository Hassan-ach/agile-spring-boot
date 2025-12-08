package com.ensa.agile.infrastructure.security.exception;

import com.ensa.agile.domain.global.exception.ApplicationException;

public class JwtCreationException extends ApplicationException {
    public JwtCreationException(String msg) {
        super(msg);
    }

    public JwtCreationException() {
        super();
    }
}
