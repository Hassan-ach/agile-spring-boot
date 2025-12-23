package com.ensa.agile.application.epic.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EpicCreateRequest {

    private String title;

    private String description;

    private String productId;

    public EpicCreateRequest(String productId, EpicCreateRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new ValidationException("title cannot be null or blank");
        }

        if (req.getDescription() == null || req.getDescription().isBlank()) {
            throw new ValidationException(
                "description cannot be null or blank");
        }

        this.title = req.getTitle();
        this.description = req.getDescription();
        this.productId = productId;
    }
}
