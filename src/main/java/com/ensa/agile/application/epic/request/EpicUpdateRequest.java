package com.ensa.agile.application.epic.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicUpdateRequest {
    private String id;
    private String productId;
    private final String title;
    private final String description;

    public EpicUpdateRequest(String productId, String id,
                             EpicUpdateRequest req) {
        this.productId = productId;
        this.id = id;
        this.title = req.getTitle();
        this.description = req.getDescription();
    }
}
