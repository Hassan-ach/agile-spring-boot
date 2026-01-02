package com.ensa.agile.infrastructure.persistence.task.jpa.history;

import com.ensa.agile.domain.task.entity.TaskHistory;
import com.ensa.agile.domain.task.repository.TaskHistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class TaskHistoryRepositoryAdapter implements TaskHistoryRepository {

    private final JpaTaskHistoryRepository jpaTaskHistoryRepository;

    @Override
    public TaskHistory save(TaskHistory entity) {
        return TaskHistoryJpaMapper.toDomainEntity(
            this.jpaTaskHistoryRepository.save(
                TaskHistoryJpaMapper.toJpaEntity(entity)));
    }

    @Override
    public TaskHistory findById(String id) {
        return TaskHistoryJpaMapper.toDomainEntity(
            this.jpaTaskHistoryRepository.findById(id).orElseThrow());
    }

    @Override
    public List<TaskHistory> findAll() {
        return this.jpaTaskHistoryRepository.findAll()
            .stream()
            .map(TaskHistoryJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void deleteById(String id) {
        this.jpaTaskHistoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return this.jpaTaskHistoryRepository.existsById(id);
    }
}
