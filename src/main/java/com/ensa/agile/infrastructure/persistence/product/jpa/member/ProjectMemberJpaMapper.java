package com.ensa.agile.infrastructure.persistence.product.jpa.member;

import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.infrastructure.persistence.product.jpa.backlog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaMapper;

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
  public static ProjectMember toDomainEntity(ProjectMemberJpaEntity jpaEntity) {
    ProjectMember domainEntity = new ProjectMember(
        jpaEntity.getId(), UserJpaMapper.toDomainEntity(jpaEntity.getUser()),
        ProductBackLogJpaMapper.toDomainEntity(jpaEntity.getProductBackLog()),
        jpaEntity.getRole(), jpaEntity.getStatus(), jpaEntity.getCreatedDate(),
        jpaEntity.getCreatedBy(), jpaEntity.getLastModifiedDate(),
        jpaEntity.getLastModifiedBy());
    return domainEntity;
  }
}
