package com.ensa.agile.infrastructure.persistence.user.jpa;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name = "is_email_verified")
	private boolean emailVerified;

	@Column(name = "is_enabled")
	private boolean enabled;

	@Column(name = "is_account_locked")
	private boolean locked;

	@Column(name = "is_crendetial_expired")
	private boolean credentialsExpired;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDate createdAt;

	public UserJpaEntity(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.emailVerified = false;
		this.enabled = true;
		this.locked = false;
		this.credentialsExpired = false;
		this.createdAt = LocalDate.now();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}
	// public String getUsername() {
	// return this.email;
	// }
	//
	// public boolean isAccountNonExpired() {
	// return false;
	// }
	//
	// public boolean isAccountNonLocked() {
	// return true;
	// }
	//
	// public boolean isCredentialsNonExpired() {
	// return false;
	// }
	//
	// public boolean isEnabled() {
	// return true;
	// }
}
