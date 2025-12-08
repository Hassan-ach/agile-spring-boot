package com.ensa.agile.domain.global.exception;

public class ForbidException extends RuntimeException {

	public ForbidException(String msg) {
		super(msg);
	}

	public ForbidException() {
		super("Permissions denied");
	}
}
