package com.ensa.agile.application.user.request;

import java.util.regex.Pattern;

import com.ensa.agile.domain.user.exception.ValidationException;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class RegisterRequest {

	@NotBlank
	@Size(min = 3)
	private String firstName;

	@NotBlank
	@Size(min = 3)
	private String lastName;

	@NotBlank
	@Email
	private String email;

	@Size(min = 8)
	private String password;

	public RegisterRequest(String firstName, String lastName, String email, String password) {
		// validateFirstname(firstName);
		// validateLastname(lastName);
		// validateEmail(email);
		// validatePassword(password);

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	private void validateFirstname(String firstName) {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new ValidationException("firstName cannot be empty");
		}
		if (firstName.length() < 3 || firstName.length() > 50) {
			throw new ValidationException("firstName must be between 3 and 50 characters");
		}
	}

	private void validateLastname(String lastName) {
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new ValidationException("lastName cannot be empty");
		}
		if (lastName.length() < 3 || lastName.length() > 50) {
			throw new ValidationException("lastName must be between 3 and 50 characters");
		}
	}

	private void validateEmail(String email) {
		Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

		if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
			throw new ValidationException("Invalid email format");
		}
	}

	private void validatePassword(String password) {
		if (password == null || password.length() < 8) {
			throw new ValidationException("Password must be at least 8 characters");
		}
	}

}
