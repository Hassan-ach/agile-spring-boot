package com.ensa.agile.application.user.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {

	@NotBlank
	public UUID id;

	@Size(min = 3)
	public String firstName;

	@Size(min = 3)
	public String lastName;

	@Email
	public String email;

	@Size(min = 8)
	public String password;

}
