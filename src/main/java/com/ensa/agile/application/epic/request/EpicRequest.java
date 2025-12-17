package com.ensa.agile.application.epic.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicRequest {
    String epicId;
    String productId;
}
