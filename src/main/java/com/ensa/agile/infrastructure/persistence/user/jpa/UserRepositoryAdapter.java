package com.ensa.agile.infrastructure.persistence.user.jpa;

import com.ensa.agile.application.user.exception.UserNotFoundException;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserJpaMapper.toJpaEntity(user);
        UserJpaEntity saved = jpaUserRepository.save(entity);
        return UserJpaMapper.toDomainEntity(saved);
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
            .map(UserJpaMapper::toDomainEntity)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return jpaUserRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findAll() {
        List<UserJpaEntity> entities = jpaUserRepository.findAll();
        return entities.stream().map(UserJpaMapper::toDomainEntity).toList();
    }

    @Override
    public User findById(String id) {
        return jpaUserRepository.findById(id)
            .map(UserJpaMapper::toDomainEntity)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public void deleteById(String id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaUserRepository.existsById(id);
    }
}
