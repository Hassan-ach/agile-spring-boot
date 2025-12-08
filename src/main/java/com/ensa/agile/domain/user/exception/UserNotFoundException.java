package com.ensa.agile.domain.user.exception;

import com.ensa.agile.domain.global.exception.NotFounException;

public class UserNotFoundException extends NotFounException {
	public UserNotFoundException(String id) {
		super("User", id);
	}

	public UserNotFoundException() {
		super();
	}
}
