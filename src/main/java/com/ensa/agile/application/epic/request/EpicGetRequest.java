package com.ensa.agile.application.epic.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicGetRequest {
    String epicId;
    String productId;
    List<String> fields;

    public EpicGetRequest(String productId, String epicId, String with) {
        this.productId = productId;
        this.epicId = epicId;
        this.fields = List.of(with.split(","))
                          .stream()
                          .map(String::trim)
                          .map(String::toUpperCase)
                          .toList();
    }
}
