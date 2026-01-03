package com.ensa.agile.infrastructure.persistence.jpa.story.userstory;

import com.ensa.agile.infrastructure.persistence.jpa.sprint.backlog.SprintBackLogJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserStoryRepository
    extends JpaRepository<UserStoryJpaEntity, String> {

    List<UserStoryJpaEntity> findAllByEpic_Id(String epicId);

    @Query(""" 
            SELECT u FROM UserStoryJpaEntity u
            WHERE u.id IN :ids
        """)
    List<UserStoryJpaEntity> findByBatch(@Param("ids") List<String> ids);

    @Modifying
    @Query(""" 
        UPDATE UserStoryJpaEntity u SET u.sprintBackLog = :sprintBackLog
        WHERE u.id IN :userStoryIds
        """)
    void assignToSprint(List<String> userStoryIds,
                        SprintBackLogJpaEntity sprintBackLog);
}
