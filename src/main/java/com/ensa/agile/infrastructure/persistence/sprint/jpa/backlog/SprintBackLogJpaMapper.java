package com.ensa.agile.infrastructure.persistence.sprint.jpa.backlog;

import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.infrastructure.persistence.product.jpa.backlog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaMapper;

public class SprintBackLogJpaMapper {
    public static SprintBackLog
    toDomainEntity(SprintBackLogJpaEntity sprintBacklogJpaEntity) {
        if (sprintBacklogJpaEntity == null) {
            return null;
        }
        // return new SprintBackLog(
        //     sprintBacklogJpaEntity.getId(), sprintBacklogJpaEntity.getName(),
        //     ProductBackLogJpaMapper.toDomainEntity(
        //         sprintBacklogJpaEntity.getProductBackLog()),
        //     UserJpaMapper.toDomainEntity(
        //         sprintBacklogJpaEntity.getScrumMaster()),
        //     sprintBacklogJpaEntity.getStatus(),
        //     sprintBacklogJpaEntity.getStartDate(),
        //     sprintBacklogJpaEntity.getEndDate(),
        //     sprintBacklogJpaEntity.getGoal(),
        //     sprintBacklogJpaEntity.getCreatedDate(),
        //     sprintBacklogJpaEntity.getCreatedBy(),
        //     sprintBacklogJpaEntity.getLastModifiedDate(),
        //     sprintBacklogJpaEntity.getLastModifiedBy());

        return SprintBackLog.builder()
            .id(sprintBacklogJpaEntity.getId())
            .name(sprintBacklogJpaEntity.getName())
            .productBackLog(ProductBackLogJpaMapper.toDomainEntity(
                sprintBacklogJpaEntity.getProductBackLog()))
            .scrumMaster(UserJpaMapper.toDomainEntity(
                sprintBacklogJpaEntity.getScrumMaster()))
            // .status(SprintBackLogHistoryJpaMapper.toDomainEntity(
            //     sprintBacklogJpaEntity.getStatus()))
            .startDate(sprintBacklogJpaEntity.getStartDate())
            .endDate(sprintBacklogJpaEntity.getEndDate())
            .goal(sprintBacklogJpaEntity.getGoal())
            .createdDate(sprintBacklogJpaEntity.getCreatedDate())
            .createdBy(sprintBacklogJpaEntity.getCreatedBy())
            .lastModifiedDate(sprintBacklogJpaEntity.getLastModifiedDate())
            .lastModifiedBy(sprintBacklogJpaEntity.getLastModifiedBy())
            .build();
    }

    public static SprintBackLogJpaEntity
    toJpaEntity(SprintBackLog sprintBackLog) {
        if (sprintBackLog == null) {
            return null;
        }
        return SprintBackLogJpaEntity.builder()
            .id(sprintBackLog.getId())
            .name(sprintBackLog.getName())
            .productBackLog(ProductBackLogJpaMapper.toJpaEntity(
                sprintBackLog.getProductBackLog()))
            .scrumMaster(
                UserJpaMapper.toJpaEntity(sprintBackLog.getScrumMaster()))
            // .status(SprintBackLogHistoryJpaMapper.toJpaEntity(
            //     sprintBackLog.getStatus()))
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
