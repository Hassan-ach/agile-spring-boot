package com.ensa.agile.application.product.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class ProductBackLogNotFoundException extends NotFoundException {

    public ProductBackLogNotFoundException(String productBackLogId) {
        super("Product", productBackLogId);
    }

    public ProductBackLogNotFoundException() { super("Product"); }
}
