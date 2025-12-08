package com.ensa.agile.domain.product.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class EpicNotFoundException extends DomainException {
    public EpicNotFoundException(String epicId) {
        super("Epic with ID " + epicId + " not found.");
    }
}
