package com.ensa.agile.infrastructure.persistence.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserJpaEntity, String> {

	Optional<UserJpaEntity> findByEmail(String email);

	boolean existsByEmailIgnoreCase(String email);

}
