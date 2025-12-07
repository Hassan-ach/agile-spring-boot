package com.ensa.agile.domain.user.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private boolean emailVerified;

	private boolean enabled;

	private boolean locked;

	private boolean credentialsExpired;

	private LocalDate createdDate;

	public User(String firstName, String lastName, String email, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		// this.projectMembers = new ArrayList<ProjectMember>();
		this.emailVerified = false;
		this.enabled = true;
		this.locked = false;
		this.credentialsExpired = false;
		this.createdDate = LocalDate.now();
	}

	public User(String id, String firstName, String lastName, String email, String password,
			Boolean emailVerified,
			Boolean enabled,
			Boolean locked, Boolean credentialsExpired, LocalDate createdDate) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		// this.projectMembers = projectMembers;
		this.emailVerified = emailVerified;
		this.enabled = enabled;
		this.locked = locked;
		this.credentialsExpired = credentialsExpired;
		this.createdDate = createdDate;
	}

	public void disable() {
		this.enabled = false;
	}

	public void enable() {
		this.enabled = true;
	}

	public void lock() {
		this.locked = true;
	}

	public void unlock() {
		this.locked = false;
	}

	public void expireCredentials() {
		this.credentialsExpired = true;
	}

	public void renewCredentials() {
		this.credentialsExpired = false;
	}

	public void verifyEmail() {
		this.emailVerified = true;
	}

	public List<String> getAuthorities() {
		return new ArrayList<String>();
	}

}
