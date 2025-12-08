package com.ensa.agile.domain.global.exception;

public class DataBaseException extends RuntimeException {
  public DataBaseException(String message) { super(message); }
  public DataBaseException() { super("Database error occurred"); }
}
