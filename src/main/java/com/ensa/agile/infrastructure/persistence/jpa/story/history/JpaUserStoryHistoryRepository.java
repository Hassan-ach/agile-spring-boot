package com.ensa.agile.infrastructure.persistence.jpa.story.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserStoryHistoryRepository
    extends JpaRepository<UserStoryHistoryJpaEntity, String> {}
