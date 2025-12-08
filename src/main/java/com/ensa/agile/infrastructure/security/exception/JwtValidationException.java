package com.ensa.agile.infrastructure.security.exception;

import com.ensa.agile.domain.global.exception.ApplicationException;

public class JwtValidationException extends ApplicationException {
  public JwtValidationException(String msg) { super(msg); }

  public JwtValidationException() { super("Token Validation Error"); }
}
