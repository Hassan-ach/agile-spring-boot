package com.ensa.agile.application.product.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class UserAlreadyInvitedException extends DomainException {
    public UserAlreadyInvitedException(String email) {
        super("User with email " + email +
              " has already been invited to the project.");
    }

    public UserAlreadyInvitedException() {
        super("User has already been invited to the project.");
    }
}
