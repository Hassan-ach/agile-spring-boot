package com.ensa.agile.domain.global.exception;

public class DataBaseTransactionException extends RuntimeException {
  public DataBaseTransactionException(String message) { super(message); }
  public DataBaseTransactionException() { super("Database error occurred"); }
}
