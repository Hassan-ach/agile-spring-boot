package com.ensa.agile.application.story.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserStoryGetRequest {

    private String id;
    private List<String> fields;

    public UserStoryGetRequest(String id, String query) {
        this.id = id;
        if (query == null || query.isBlank()) {
            this.fields = List.of();
        } else {
            this.fields = List.of(query.split(","))
                              .stream()
                              .map(String::trim)
                              .map(String::toUpperCase)
                              .toList();
        }
    }
}
