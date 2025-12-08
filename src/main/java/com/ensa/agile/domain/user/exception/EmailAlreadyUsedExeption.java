package com.ensa.agile.domain.user.exception;

import com.ensa.agile.domain.global.exception.AlreadyExistException;

public class EmailAlreadyUsedExeption extends AlreadyExistException {

	public EmailAlreadyUsedExeption(String email) {
		super("this email: " + email + " already used");
	}

	public EmailAlreadyUsedExeption(){
		super("Email already used");
	}
}
