package com.ensa.agile.domain.global.exception;

public class NotFounException extends DomainException {
	public NotFounException(String entityName, String id) {
		super(entityName + " not found with ID: " + id);
	}


	public NotFounException() {
		super("Resource not found.");
	}

}
