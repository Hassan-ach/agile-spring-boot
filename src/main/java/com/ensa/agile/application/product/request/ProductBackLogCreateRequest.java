package com.ensa.agile.application.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogCreateRequest {
  @NotBlank private final String name;

  @NotBlank private final String description;
}
