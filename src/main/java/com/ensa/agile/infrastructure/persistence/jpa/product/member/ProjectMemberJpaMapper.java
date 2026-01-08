package com.ensa.agile.infrastructure.persistence.jpa.product.member;

import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.infrastructure.persistence.jpa.product.backlog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.jpa.user.UserJpaMapper;

public class ProjectMemberJpaMapper {

    public static ProjectMemberJpaEntity
    toJpaEntity(ProjectMember projectMember) {
        ProjectMemberJpaEntity jpaEntity =
            ProjectMemberJpaEntity.builder()
                .id(projectMember.getId())
                .user(UserJpaMapper.toJpaEntity(projectMember.getUser()))
                .productBackLog(ProductBackLogJpaMapper.toJpaEntity(
                    projectMember.getProductBackLog()))
                .role(projectMember.getRole())
                .status(projectMember.getStatus())
                .build();
        return jpaEntity;
    }
    public static ProjectMember
    toDomainEntity(ProjectMemberJpaEntity jpaEntity) {

        return ProjectMember.builder()
            .id(jpaEntity.getId())
            .user(UserJpaMapper.toDomainEntity(jpaEntity.getUser()))
            .productBackLog(ProductBackLogJpaMapper.toDomainEntity(
                jpaEntity.getProductBackLog()))
            .role(jpaEntity.getRole())
            .status(jpaEntity.getStatus())
            .createdDate(jpaEntity.getCreatedDate())
            .createdBy(jpaEntity.getCreatedBy())
            .lastModifiedDate(jpaEntity.getLastModifiedDate())
            .lastModifiedBy(jpaEntity.getLastModifiedBy())
            .build();
    }
}
