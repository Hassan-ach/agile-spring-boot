package com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog;

import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaMapper;

public class SprintBackLogJpaMapper {
    public static SprintBackLog
    toDomainEntity(SprintBackLogJpaEntity sprintBacklogJpaEntity) {
        return new SprintBackLog(
            sprintBacklogJpaEntity.getId(), sprintBacklogJpaEntity.getName(),
            ProductBackLogJpaMapper.toDomainEntity(
                sprintBacklogJpaEntity.getProductBackLog()),
            UserJpaMapper.toDomainEntity(
                sprintBacklogJpaEntity.getScrumMaster()),
            sprintBacklogJpaEntity.getStatus(),
            sprintBacklogJpaEntity.getStartDate(),
            sprintBacklogJpaEntity.getEndDate(),
            sprintBacklogJpaEntity.getGoal(),
            sprintBacklogJpaEntity.getCreatedDate(),
            sprintBacklogJpaEntity.getCreatedBy(),
            sprintBacklogJpaEntity.getLastModifiedDate(),
            sprintBacklogJpaEntity.getLastModifiedBy());
    }

    public static SprintBackLogJpaEntity
    toJpaEntity(SprintBackLog sprintBackLog) {
        return SprintBackLogJpaEntity.builder()
            .id(sprintBackLog.getId())
            .name(sprintBackLog.getName())
            .productBackLog(ProductBackLogJpaMapper.toJpaEntity(
                sprintBackLog.getProductBackLog()))
            .scrumMaster(
                UserJpaMapper.toJpaEntity(sprintBackLog.getScrumMaster()))
            .status(sprintBackLog.getStatus())
            .startDate(sprintBackLog.getStartDate())
            .endDate(sprintBackLog.getEndDate())
            .goal(sprintBackLog.getGoal())
            .createdDate(sprintBackLog.getCreatedDate())
            .createdBy(sprintBackLog.getCreatedBy())
            .lastModifiedDate(sprintBackLog.getLastModifiedDate())
            .lastModifiedBy(sprintBackLog.getLastModifiedBy())
            .build();
    }
}
