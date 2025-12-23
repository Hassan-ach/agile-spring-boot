package com.ensa.agile.application.product.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductBackLogCreateRequest {
    private String name;

    private String description;

    // This contsructor is for validation purposes
    public ProductBackLogCreateRequest(ProductBackLogCreateRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (req.name == null || req.name.isEmpty()) {
            throw new ValidationException("Name cannot be null or empty");
        }
        if (req.description == null || req.description.isEmpty()) {
            throw new ValidationException(
                "Description cannot be null or empty");
        }

        this.name = req.name;
        this.description = req.description;
    }
}
