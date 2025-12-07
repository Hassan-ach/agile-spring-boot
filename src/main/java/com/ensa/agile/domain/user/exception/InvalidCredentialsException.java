package com.ensa.agile.domain.user.exception;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String message) {
		super("Invalid credentials " + message);
	}

	public InvalidCredentialsException() {
		super("Invalid credentials ");
	}

}
