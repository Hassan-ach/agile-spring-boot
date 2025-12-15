package com.ensa.agile.application.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class ProductBackLogGetRequest {
    @NotBlank private final String id;
}
