package com.ensa.agile.infrastructure.persistence.epic.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEpicRepository
    extends JpaRepository<EpicJpaEntity, String> {
    List<EpicJpaEntity> findAllByProductBackLog_Id(String projectId);
}
