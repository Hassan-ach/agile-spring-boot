package com.ensa.agile.application.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogResponse {

    private final String id;

    private final String name;

    private final String description;


}
