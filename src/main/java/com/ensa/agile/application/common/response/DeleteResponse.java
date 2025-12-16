package com.ensa.agile.application.common.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class DeleteResponse {

    private final String message;

    private final boolean success;
}
