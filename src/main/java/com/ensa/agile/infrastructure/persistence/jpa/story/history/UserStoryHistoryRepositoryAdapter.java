package com.ensa.agile.infrastructure.persistence.jpa.story.history;

import com.ensa.agile.application.story.exception.UserStoryHistoryNotFoundException;
import com.ensa.agile.domain.story.entity.UserStoryHistory;
import com.ensa.agile.domain.story.repository.UserStoryHistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserStoryHistoryRepositoryAdapter
    implements UserStoryHistoryRepository {

    private final JpaUserStoryHistoryRepository jpaUserStoryHistoryRepository;

    @Override
    public UserStoryHistory save(UserStoryHistory entity) {
        return UserStoryHistoryJpaMapper.toDomainEntity(
            jpaUserStoryHistoryRepository.save(
                UserStoryHistoryJpaMapper.toJpaEntity(entity)));
    }

    @Override
    public UserStoryHistory findById(String s) {
        return jpaUserStoryHistoryRepository.findById(s)
            .map(UserStoryHistoryJpaMapper::toDomainEntity)
            .orElseThrow(UserStoryHistoryNotFoundException::new);
    }

    @Override
    public List<UserStoryHistory> findAll() {
        return jpaUserStoryHistoryRepository.findAll()
            .stream()
            .map(UserStoryHistoryJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void deleteById(String s) {
        jpaUserStoryHistoryRepository.deleteById(s);
    }

    @Override
    public boolean existsById(String s) {
        return jpaUserStoryHistoryRepository.existsById(s);
    }
}
