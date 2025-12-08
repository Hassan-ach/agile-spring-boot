package com.ensa.agile.application.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogUpdateRequest {

    @NotBlank
    private final String id;

    private final String name;

    private final String description;

}
