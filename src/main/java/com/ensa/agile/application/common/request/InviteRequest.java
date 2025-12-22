package com.ensa.agile.application.common.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InviteRequest {
    @NotBlank private String email;
    private String productId;

    public InviteRequest(String productId, InviteRequest req) {
        this.productId = productId;
        this.email = req.getEmail();
    }

    public InviteRequest(String email) {
        this.email = email;
        this.productId = null;
    }
}
