package com.ensa.agile.application.product.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductBackLogGetRequest {
    private String id;
    private List<String> fields;
    public ProductBackLogGetRequest(String id, String with) {
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
