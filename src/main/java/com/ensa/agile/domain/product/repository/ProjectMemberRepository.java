package com.ensa.agile.domain.product.repository;

import com.ensa.agile.domain.global.repository.BaseDomainRepository;
import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.domain.product.enums.RoleType;

public interface ProjectMemberRepository
    extends BaseDomainRepository<ProjectMember, String> {

    boolean existsByProductBackLogIdAndUserId(String projectId, String userId);

    boolean existsByUserEmailAndProductBackLogId(String userEmail,
                                                 String productBackLogId);

    boolean existsByUserIdAndProductBackLogIdAndRole(String userId,
                                                     String productBackLogId,
                                                     RoleType role);
}
