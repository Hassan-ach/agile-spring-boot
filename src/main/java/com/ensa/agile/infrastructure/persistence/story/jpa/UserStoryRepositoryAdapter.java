package com.ensa.agile.infrastructure.persistence.story.jpa;

import com.ensa.agile.application.story.exception.UserStoryNotFounException;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserStoryRepositoryAdapter implements UserStoryRepository {

    private final JpaUserStoryRepository jpaUserStoryRepository;

    @Override
    public UserStory save(UserStory entity) {
        return UserStoryJpaMapper.toDomainEntity(
            this.jpaUserStoryRepository.save(
                UserStoryJpaMapper.toJpaEntity(entity)));
    }

    @Override
    public UserStory findById(String s) {
        return UserStoryJpaMapper.toDomainEntity(
            this.jpaUserStoryRepository.findById(s).orElseThrow(
                    UserStoryNotFounException::new
            ));
    }

    @Override
    public List<UserStory> findAll() {
        return this.jpaUserStoryRepository.findAll()
            .stream()
            .map(UserStoryJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void deleteById(String s) {
        this.jpaUserStoryRepository.deleteById(s);
    }

    @Override
    public boolean existsById(String s) {
        return this.jpaUserStoryRepository.existsById(s);
    }
}
