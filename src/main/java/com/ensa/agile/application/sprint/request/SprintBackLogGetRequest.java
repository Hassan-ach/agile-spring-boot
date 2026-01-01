package com.ensa.agile.application.sprint.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SprintBackLogGetRequest {
    private String id;
    private List<String> fields;

    public SprintBackLogGetRequest(String id, String with) {
        this.id = id;
        if (with == null || with.isBlank()) {
            this.fields = List.of();
        } else {
            this.fields = List.of(with.split(","))
                              .stream()
                              .map(String::trim)
                              .map(String::toUpperCase)
                              .toList();
        }
    }
}
