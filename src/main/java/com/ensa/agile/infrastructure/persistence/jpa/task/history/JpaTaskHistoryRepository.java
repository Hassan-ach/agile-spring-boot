package com.ensa.agile.infrastructure.persistence.jpa.task.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskHistoryRepository
    extends JpaRepository<TaskHistoryJpaEntity, String> {}
