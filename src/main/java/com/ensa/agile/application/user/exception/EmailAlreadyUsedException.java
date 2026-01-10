package com.ensa.agile.application.user.exception;

import com.ensa.agile.domain.global.exception.AlreadyExistsException;

public class EmailAlreadyUsedException extends AlreadyExistsException {

    public EmailAlreadyUsedException(String email) {
        super("this email: " + email + " already used");
    }

    public EmailAlreadyUsedException() { super("Email already used"); }
}
