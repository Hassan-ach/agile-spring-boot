package com.ensa.agile.infrastructure.persistence.jpa.task.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskJpaEntity, String> {

}
