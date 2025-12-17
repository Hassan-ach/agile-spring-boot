package com.ensa.agile.application.epic.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EpicCreateRequest {

    @NotBlank private String title;

    @NotBlank private String description;

    private String productId;

    public EpicCreateRequest(String productId, EpicCreateRequest req) {
        this.productId = productId;
        this.title = req.getTitle();
        this.description = req.getDescription();
    }
}
