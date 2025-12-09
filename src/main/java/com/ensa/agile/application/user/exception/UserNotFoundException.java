package com.ensa.agile.application.user.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String id) { super("User", id); }

    public UserNotFoundException() { super("User"); }
}
