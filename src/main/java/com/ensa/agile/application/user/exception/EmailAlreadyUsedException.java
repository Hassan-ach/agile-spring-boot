package com.ensa.agile.application.user.exception;

import com.ensa.agile.domain.global.exception.AlreadyExistException;

public class EmailAlreadyUsedException extends AlreadyExistException {

    public EmailAlreadyUsedException(String email) {
        super("this email: " + email + " already used");
    }

    public EmailAlreadyUsedException() { super("Email already used"); }
}
