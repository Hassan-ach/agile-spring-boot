package com.ensa.agile.infrastructure.persistence.story.jpa;

import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.infrastructure.persistence.epic.jpa.EpicJpaMapper;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog.SprintBackLogJpaMapper;

public class UserStoryJpaMapper {

    public static UserStoryJpaEntity toJpaEntity(UserStory entity) {
        var us = UserStoryJpaEntity.builder()
                     .id(entity.getId())
                     .title(entity.getTitle())
                     .description(entity.getDescription())
                     .status(entity.getStatus())
                     .priority(entity.getPriority())
                     .storyPoints(entity.getStoryPoints())
                     .acceptanceCriteria(entity.getAcceptanceCriteria())
                     .productBackLog(ProductBackLogJpaMapper.toJpaEntity(
                         entity.getProductBackLog()))
                     .createdDate(entity.getCreatedDate())
                     .createdBy(entity.getCreatedBy())
                     .lastModifiedDate(entity.getLastModifiedDate())
                     .lastModifiedBy(entity.getLastModifiedBy())
                     .build();

        if (entity.getEpic() != null) {
            us.setEpic(EpicJpaMapper.toJpaEntity(entity.getEpic()));
        }
        if (entity.getSprintBackLog() != null) {
            us.setSprintBackLog(
                SprintBackLogJpaMapper.toJpaEntity(entity.getSprintBackLog()));
        }
        return us;
    }

    public static UserStory toDomainEntity(UserStoryJpaEntity jpaEntity) {
        var us = new UserStory(
            jpaEntity.getId(), jpaEntity.getTitle(), jpaEntity.getDescription(),
            jpaEntity.getPriority(), jpaEntity.getStatus(),
            jpaEntity.getStoryPoints(), jpaEntity.getAcceptanceCriteria(),
            ProductBackLogJpaMapper.toDomainEntity(
                jpaEntity.getProductBackLog()),
            jpaEntity.getCreatedDate(), jpaEntity.getCreatedBy(),
            jpaEntity.getLastModifiedDate(), jpaEntity.getLastModifiedBy());

        if (jpaEntity.getEpic() != null) {
            us.setEpic(EpicJpaMapper.toDomainEntity(jpaEntity.getEpic()));
        }
        if (jpaEntity.getSprintBackLog() != null) {
            us.setSprintBackLog(SprintBackLogJpaMapper.toDomainEntity(
                jpaEntity.getSprintBackLog()));
        }
        return us;
    }
}
