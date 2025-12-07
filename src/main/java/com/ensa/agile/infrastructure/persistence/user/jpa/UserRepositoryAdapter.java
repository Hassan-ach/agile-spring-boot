package com.ensa.agile.infrastructure.persistence.user.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.exception.UserNotFoundException;
import com.ensa.agile.domain.user.repository.UserRepository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

	private final JpaUserRepository jpaUserRepository;

	public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
		this.jpaUserRepository = jpaUserRepository;
	}

	@Override
	public User save(User user) {
		UserJpaEntity entity = UserJpaMapper.toJpaEntity(user);
		UserJpaEntity saved = jpaUserRepository.save(entity);
		return UserJpaMapper.toDomainEntity(saved);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return jpaUserRepository.findByEmail(email)
				.map(UserJpaMapper::toDomainEntity);
	}

	@Override
	public boolean existsByEmailIgnoreCase(String email) {
		return jpaUserRepository.existsByEmailIgnoreCase(email);
	}

	@Override
	public List<User> findAll() {
		List<UserJpaEntity> entities = jpaUserRepository.findAll();
		return entities.stream()
				.map(UserJpaMapper::toDomainEntity)
				.toList();
	}

	@Override
	public Optional<User> findById(String id) {
		return jpaUserRepository.findById(id)
				.map(UserJpaMapper::toDomainEntity);
	}

	@Override
	public void deleteById(String id) {
		jpaUserRepository.deleteById(id);
	}

	@Override
	public boolean existsById(String id) {
		return jpaUserRepository.existsById(id);
	}

	public User loadUserByEmail(String email) {
		if (!jpaUserRepository.existsByEmailIgnoreCase(email)) {
			throw new UserNotFoundException(email);
		}
		return UserJpaMapper.toDomainEntity(jpaUserRepository.findByEmail(email).orElseThrow(
				() -> new UserNotFoundException(email)));
	}

}
