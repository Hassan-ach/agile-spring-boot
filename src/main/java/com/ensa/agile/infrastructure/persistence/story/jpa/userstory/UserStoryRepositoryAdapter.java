package com.ensa.agile.infrastructure.persistence.story.jpa.userstory;

import com.ensa.agile.application.story.exception.UserStoryNotFoundException;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.backlog.SprintBackLogJpaMapper;
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
                UserStoryNotFoundException::new));
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

    @Override
    public List<UserStory> findAllByEpicId(String epicId) {
        return this.jpaUserStoryRepository.findAllByEpic_Id(epicId)
            .stream()
            .map(UserStoryJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public List<UserStory> findByBatch(List<String> ids) {
        return this.jpaUserStoryRepository.findByBatch(ids)
            .stream()
            .map(UserStoryJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void assignToSprint(List<String> userStoryIds,
                               SprintBackLog sprintBackLog) {
        this.jpaUserStoryRepository.assignToSprint(
            userStoryIds, SprintBackLogJpaMapper.toJpaEntity(sprintBackLog));
    }
}
