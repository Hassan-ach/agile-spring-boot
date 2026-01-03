package com.ensa.agile.infrastructure.persistence.jpa.product.member;

import com.ensa.agile.domain.product.enums.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProjectMemberRepository
    extends JpaRepository<ProjectMemberJpaEntity, String> {

    boolean existsByUser_IdAndProductBackLog_Id(String userId,
                                                String productBackLogId);

    boolean existsByUser_EmailAndProductBackLog_Id(String userEmail,
                                                   String productBackLogId);
    boolean existsByUser_IdAndProductBackLog_IdAndRole(String userId,
                                                       String productBackLogId,
                                                       RoleType role);

    boolean existsByUser_EmailAndProductBackLog_IdAndRole(
        String userEmail, String productBackLogId, RoleType role);

    void deleteByUser_EmailAndProductBackLog_Id(String userEmail,
                                                String productBackLogId);

    Optional<ProjectMemberJpaEntity>
    findByUser_IdAndProductBackLog_Id(String userId, String productBackLogId);
}
