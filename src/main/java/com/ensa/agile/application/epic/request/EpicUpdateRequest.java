package com.ensa.agile.application.epic.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicUpdateRequest {
    private String id;
    private final String title;
    private final String description;
}
