package com.ensa.agile.application.product.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class ProductBackLogGetRequest {
    private String id;
    private String with;
    public ProductBackLogGetRequest(String id, String with) {
        this.id = id;

        if (with == null || with.isBlank()) {
            this.with = "";
        } else {
            this.with = with;
        }
    }
}
