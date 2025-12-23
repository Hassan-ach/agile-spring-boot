package com.ensa.agile.application.user.request;

import com.ensa.agile.application.common.utils.ValidationUtil;
import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // This constructor is for validation purposes
    public RegisterRequest(RegisterRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (req.firstName == null || req.firstName.trim().isEmpty() ||
            req.firstName.length() < 3) {
            throw new ValidationException(
                "First name must be at least 3 characters long and cannot be "
                + "blank");
        }
        if (req.lastName == null || req.lastName.trim().isEmpty() ||
            req.lastName.length() < 3) {
            throw new ValidationException(
                "Last name must be at least 3 characters long and cannot be "
                + "blank");
        }
        if (req.email == null || req.email.trim().isEmpty() ||
            !ValidationUtil.isValidEmail(req.email)) {
            throw new ValidationException("Email must be valid and cannot be "
                                          + "blank");
        }
        if (req.password != null && req.password.length() < 8) {
            throw new ValidationException(
                "Password must be at least 8 characters long if provided");
        }
        this.firstName = req.firstName;
        this.lastName = req.lastName;
        this.email = req.email;
        this.password = req.password;
    }
}
