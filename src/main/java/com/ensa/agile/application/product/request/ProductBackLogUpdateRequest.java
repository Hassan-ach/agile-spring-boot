package com.ensa.agile.application.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductBackLogUpdateRequest {

    private String id;

    private String name;

    private String description;

    public ProductBackLogUpdateRequest(String id,
                                       ProductBackLogUpdateRequest req) {
        this.id = id;
        this.name = req.getName();
        this.description = req.getDescription();
    }
}
