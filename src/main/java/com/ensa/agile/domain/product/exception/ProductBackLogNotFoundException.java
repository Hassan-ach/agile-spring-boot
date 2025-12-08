package com.ensa.agile.domain.product.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class ProductBackLogNotFoundException extends DomainException {

    public ProductBackLogNotFoundException(String productBackLogId) {
        super("Product BackLog with ID " + productBackLogId + " not found.");
    }

    public ProductBackLogNotFoundException() {
        super("Product BackLog not found.");
    }
}
