package com.ensa.agile.domain.user.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class ValidationException extends DomainException {
	public ValidationException(String message) {
		super(message);
	}
}
