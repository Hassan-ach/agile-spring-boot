package com.ensa.agile.domain.global.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, String id) {
        super(entityName + " not found with ID: " + id);
    }
    public NotFoundException(String entityName) {
        super(entityName + " not found");
    }

    public NotFoundException() { super("Resource not found."); }
}
