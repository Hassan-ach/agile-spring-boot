package com.ensa.agile.application.common.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class InviteRequest {
    @NotBlank private final String email;

    @NotBlank private final String productId;
}
