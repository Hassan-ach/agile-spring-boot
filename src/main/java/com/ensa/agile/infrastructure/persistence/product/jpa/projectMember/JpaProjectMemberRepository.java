package com.ensa.agile.infrastructure.persistence.product.jpa.projectMember;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProjectMemberRepository
    extends JpaRepository<ProjectMemberJpaEntity, String> {}
