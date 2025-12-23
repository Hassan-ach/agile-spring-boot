package com.ensa.agile.application.user.request;

import com.ensa.agile.domain.global.exception.ValidationException;
import java.util.UUID;

public class UpdateProfileRequest {

    public UUID id;
    public String firstName;
    public String lastName;
    public String password;

    // This constructor is for validation purposes
    public UpdateProfileRequest(UpdateProfileRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (req.id == null) {
            throw new ValidationException("ID cannot be null");
        }
        if (req.firstName == null || req.firstName.length() < 3) {
            throw new ValidationException(
                "First name must be at least 3 characters long");
        }
        if (req.lastName == null || req.lastName.length() < 3) {
            throw new ValidationException(
                "Last name must be at least 3 characters long");
        }
        if (req.password == null || req.password.length() < 8) {
            throw new ValidationException(
                "Password must be at least 8 characters long");
        }
        this.id = req.id;
        this.firstName = req.firstName;
        this.lastName = req.lastName;
        this.password = req.password;
    }
}
