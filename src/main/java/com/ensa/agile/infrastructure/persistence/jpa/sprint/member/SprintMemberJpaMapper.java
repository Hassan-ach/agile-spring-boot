package com.ensa.agile.infrastructure.persistence.jpa.sprint.member;

import com.ensa.agile.domain.sprint.entity.SprintMember;
import com.ensa.agile.infrastructure.persistence.jpa.sprint.backlog.SprintBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.jpa.user.UserJpaMapper;

public class SprintMemberJpaMapper {
    public static SprintMember toDomainEntity(SprintMemberJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return SprintMember.builder()
            .id(jpaEntity.getId())
            .user(UserJpaMapper.toDomainEntity(jpaEntity.getUser()))
            .sprintBackLog(SprintBackLogJpaMapper.toDomainEntity(
                jpaEntity.getSprintBackLog()))
            .createdDate(jpaEntity.getCreatedDate())
            .createdBy(jpaEntity.getCreatedBy())
            .lastModifiedDate(jpaEntity.getLastModifiedDate())
            .lastModifiedBy(jpaEntity.getLastModifiedBy())
            .build();
    }

    public static SprintMemberJpaEntity toJpaEntity(SprintMember domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        return SprintMemberJpaEntity.builder()
            .id(domainEntity.getId())
            .user(UserJpaMapper.toJpaEntity(domainEntity.getUser()))
            .sprintBackLog(SprintBackLogJpaMapper.toJpaEntity(
                domainEntity.getSprintBackLog()))
            .createdDate(domainEntity.getCreatedDate())
            .createdBy(domainEntity.getCreatedBy())
            .lastModifiedDate(domainEntity.getLastModifiedDate())
            .lastModifiedBy(domainEntity.getLastModifiedBy())
            .build();
    }
}
