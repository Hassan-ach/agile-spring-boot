package com.ensa.agile.application.common.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class InviteRequest {
    @NotBlank private final String email;
    private String productId;

    public InviteRequest(String productId, InviteRequest req) {
        this.productId = productId;
        this.email = req.getEmail();
    }
}
