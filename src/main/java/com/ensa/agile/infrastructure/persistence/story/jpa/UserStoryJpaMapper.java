package com.ensa.agile.infrastructure.persistence.story.jpa;

import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.infrastructure.persistence.epic.jpa.EpicJpaMapper;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog.SprintBackLogJpaMapper;

public class UserStoryJpaMapper {

    public static UserStoryJpaEntity toJpaEntity(UserStory entity) {
        return UserStoryJpaEntity.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .status(entity.getStatus())
            .priority(entity.getPriority())
            .storyPoints(entity.getStoryPoints())
            .acceptanceCriteria(entity.getAcceptanceCriteria())
            .epic(EpicJpaMapper.toJpaEntity(entity.getEpic()))
            .productBackLog(
                ProductBackLogJpaMapper.toJpaEntity(entity.getProductBackLog()))
            .sprintBacklog(
                SprintBackLogJpaMapper.toJpaEntity(entity.getSprintBackLog()))
            .createdDate(entity.getCreatedDate())
            .createdBy(entity.getCreatedBy())
            .lastModifiedDate(entity.getLastModifiedDate())
            .lastModifiedBy(entity.getLastModifiedBy())
            .build();
    }

    public static UserStory toDomainEntity(UserStoryJpaEntity jpaEntity) {
        return new UserStory(
            jpaEntity.getId(), jpaEntity.getTitle(), jpaEntity.getDescription(),
            jpaEntity.getPriority(), jpaEntity.getStatus(),
            jpaEntity.getStoryPoints(), jpaEntity.getAcceptanceCriteria(),
            EpicJpaMapper.toDomainEntity(jpaEntity.getEpic()),
            ProductBackLogJpaMapper.toDomainEntity(
                jpaEntity.getProductBackLog()),
            SprintBackLogJpaMapper.toDomainEntity(jpaEntity.getSprintBacklog()),
            jpaEntity.getCreatedDate(), jpaEntity.getCreatedBy(),
            jpaEntity.getLastModifiedDate(), jpaEntity.getLastModifiedBy());
    }
}
