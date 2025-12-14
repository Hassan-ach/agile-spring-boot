package com.ensa.agile.application.common.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class RemoveResponse {
    private final String message;
    private final boolean success;
}
