package com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintMembers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSprintMemberRepository
    extends JpaRepository<SprintMemberJpaEntity, String> {
    boolean existsBySprintBackLog_IdAndUser_Id(String sprintId, String userId);
}
