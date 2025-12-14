package com.ensa.agile.infrastructure.persistence.sprint.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSprintBacklogRepository extends JpaRepository<SprintBacklogJpaEntity, String> {

}
