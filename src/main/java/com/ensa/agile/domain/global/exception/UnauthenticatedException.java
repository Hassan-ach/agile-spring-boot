package com.ensa.agile.domain.global.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String message) { super(message); }
    public UnauthenticatedException() { super("Unauthenticated access"); }
}
