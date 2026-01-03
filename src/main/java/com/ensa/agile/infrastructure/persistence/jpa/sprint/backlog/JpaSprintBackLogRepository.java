package com.ensa.agile.infrastructure.persistence.jpa.sprint.backlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensa.agile.domain.sprint.enums.SprintStatus;

@Repository
public interface JpaSprintBackLogRepository
    extends JpaRepository<SprintBackLogJpaEntity, String> {

    boolean existsByStatus(SprintStatus status);
}
