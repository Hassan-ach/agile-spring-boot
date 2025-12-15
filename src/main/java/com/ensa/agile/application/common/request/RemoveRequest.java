package com.ensa.agile.application.common.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RemoveRequest {
    @NotBlank private final String email;
    private String productId;
}
