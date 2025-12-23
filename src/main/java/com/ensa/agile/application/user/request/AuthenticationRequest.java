package com.ensa.agile.application.user.request;

import com.ensa.agile.application.common.utils.ValidationUtil;
import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;

    private String password;

    // This constructor is for validation purposes
    public AuthenticationRequest(AuthenticationRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (req.email == null || req.email.trim().isEmpty() ||
            !ValidationUtil.isValidEmail(req.email)) {
            throw new ValidationException("Email must be valid and cannot be "
                                          + "blank");
        }

        if (req.password == null || req.password.trim().isEmpty()) {
            throw new ValidationException("Password cannot be blank");
        }
        this.email = req.email;
        this.password = req.password;
    }
}
