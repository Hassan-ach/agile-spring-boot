package com.ensa.agile.application.epic.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicRequest {
    String epicId;
    String productId;

    public EpicRequest(String productId, EpicRequest req) {
        if (req == null) {
            throw new ValidationException("request cannot be null");
        }
        if (req.getEpicId() == null || req.getEpicId().isBlank()) {
            throw new ValidationException("epicId cannot be null or blank");
        }
        if (productId == null || productId.isBlank()) {
            throw new ValidationException("productId cannot be null or blank");
        }
        this.productId = productId;
        this.epicId = req.getEpicId();
    }
}
