package com.ensa.agile.application.user.exception;

import com.ensa.agile.domain.global.exception.ValidationException;

public class InvalidCredentialsException extends ValidationException {
	public InvalidCredentialsException(String message) {
		super("Invalid credentials " + message);
	}

	public InvalidCredentialsException() {
		super("Invalid credentials ");
	}
}
