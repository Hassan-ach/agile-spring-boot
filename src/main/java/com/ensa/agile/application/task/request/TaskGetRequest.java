package com.ensa.agile.application.task.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskGetRequest {
    private String id;
    private List<String> fields;

    public TaskGetRequest(String id, String with) {
        this.id = id;
        this.fields = List.of(with.split(","))
                          .stream()
                          .map(String::trim)
                          .map(String::toUpperCase)
                          .toList();
    }
}
