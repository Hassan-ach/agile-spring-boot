package com.ensa.agile.infrastructure.persistence.product.jpa.projectMember;

import com.ensa.agile.domain.product.enums.RoleType;
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
}
