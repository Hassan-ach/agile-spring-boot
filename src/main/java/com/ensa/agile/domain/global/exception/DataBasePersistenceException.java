package com.ensa.agile.domain.global.exception;

public class DataBasePersistenceException extends RuntimeException {
    public DataBasePersistenceException(String message) { super(message); }
    public DataBasePersistenceException() {
        super("Error while persisting data to the database");
    }
}
