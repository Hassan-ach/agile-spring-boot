package com.ensa.agile.domain.product.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class IllegalStateException extends DomainException {

    public IllegalStateException(String message) {
        super(message);
    }
}
