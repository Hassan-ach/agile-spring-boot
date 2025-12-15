package com.ensa.agile.application.epic.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicCreateRequest {

    @NotBlank private final String title;

    @NotBlank private final String description;

    private String productId;
}
