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
}
