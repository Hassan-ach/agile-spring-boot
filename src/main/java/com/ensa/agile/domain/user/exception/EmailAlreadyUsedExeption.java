package com.ensa.agile.domain.user.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class EmailAlreadyUsedExeption extends DomainException {

	public EmailAlreadyUsedExeption(String email) {
		super("this email: " + email + " already used");
	}
}
