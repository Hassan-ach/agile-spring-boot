package com.ensa.agile.application.user.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.global.utils.ValidationUtil;
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
            throw new ValidationException("Request cannot be null");
        }

        if (req.email == null || req.email.trim().isBlank() ||
            !ValidationUtil.isValidEmail(req.email)) {
            throw new ValidationException("Email must be valid and cannot be "
                                          + "blank");
        }

        if (req.password == null || req.password.trim().isBlank()) {
            throw new ValidationException("Password cannot be blank");
        }
        this.email = req.email;
        this.password = req.password;
    }
}
