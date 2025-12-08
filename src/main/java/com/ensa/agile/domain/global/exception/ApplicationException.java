package com.ensa.agile.domain.global.exception;

public class ApplicationException extends RuntimeException {

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException() {
		super("Internal Server Error");
	}
}
