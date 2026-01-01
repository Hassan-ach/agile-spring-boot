package com.ensa.agile.application.epic.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicUpdateRequest {
    private String id;
    private String productId;
    private String title;
    private String description;

    public EpicUpdateRequest(String productId, String id,
                             EpicUpdateRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        if (req.getTitle() == null && req.getDescription() == null) {
            throw new ValidationException("title cannot be null or blank");
        }

        if (req.getTitle() != null) {
            if (req.getTitle().isBlank()) {
                throw new ValidationException("title cannot be blank");
            } else {
                this.title = req.getTitle();
            }
        }

        if (req.getDescription() != null) {
            if (req.getDescription().isBlank()) {
                throw new ValidationException("description cannot be blank");
            } else {
                this.description = req.getDescription();
            }
        }

        this.productId = productId;
        this.id = id;
    }
}
