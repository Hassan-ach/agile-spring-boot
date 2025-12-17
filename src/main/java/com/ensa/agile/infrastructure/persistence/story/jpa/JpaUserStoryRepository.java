package com.ensa.agile.infrastructure.persistence.story.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserStoryRepository
    extends JpaRepository<UserStoryJpaEntity, String> {

    List<UserStoryJpaEntity> findAllByEpic_Id(String epicId);
}
