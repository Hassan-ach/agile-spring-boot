package com.ensa.agile.domain.user.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class UserNotFoundException extends DomainException {
	public UserNotFoundException(String id) {
		super("User not found with : " + id);
	}
}
