package com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog;

import com.ensa.agile.domain.sprint.enums.SprintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSprintBackLogRepository
    extends JpaRepository<SprintBackLogJpaEntity, String> {
    boolean existsByStatus(SprintStatus status);
}
