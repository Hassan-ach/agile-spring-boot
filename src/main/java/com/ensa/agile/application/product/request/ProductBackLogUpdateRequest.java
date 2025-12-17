package com.ensa.agile.application.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogUpdateRequest {

    private String id;

    private final String name;

    private final String description;

    public ProductBackLogUpdateRequest(String id,
                                       ProductBackLogUpdateRequest req) {
        this.id = id;
        this.name = req.getName();
        this.description = req.getDescription();
    }
}
