package com.ensa.agile.application.common.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveRequest {
    @NotBlank private String email;
    private String productId;

    public RemoveRequest(String productId, RemoveRequest req) {
        this.productId = productId;
        this.email = req.getEmail();
    }

    public RemoveRequest(String email) {
        this.email = email;
        this.productId = null;
    }
}
