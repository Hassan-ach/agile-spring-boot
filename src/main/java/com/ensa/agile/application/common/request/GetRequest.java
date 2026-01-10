package com.ensa.agile.application.common.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class GetRequest {

    private String id;
    private List<String> fields;

    public GetRequest(String id, String with) {
        if (id == null || id.isBlank()) {
            throw new ValidationException("ID cannot be null or blank");
        }
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
