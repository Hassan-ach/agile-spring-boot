package com.ensa.agile.application.user.request;

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
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}
