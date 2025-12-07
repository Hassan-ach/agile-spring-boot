package com.ensa.agile.domain.user.repository;

import java.util.Optional;

import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.global.repository.BaseDomainRepository;

public interface UserRepository extends BaseDomainRepository<User, String> {
	Optional<User> findByEmail(String email);

	boolean existsByEmailIgnoreCase(String email);

	User loadUserByEmail(String email);
}
