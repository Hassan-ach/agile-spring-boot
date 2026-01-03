package com.ensa.agile.infrastructure.persistence.jpa.story.userstory;

import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.infrastructure.persistence.jpa.epic.EpicJpaMapper;
import com.ensa.agile.infrastructure.persistence.jpa.product.backlog.ProductBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.jpa.sprint.backlog.SprintBackLogJpaMapper;

public class UserStoryJpaMapper {

    public static UserStoryJpaEntity toJpaEntity(UserStory entity) {
        var us = UserStoryJpaEntity.builder()
                     .id(entity.getId())
                     .title(entity.getTitle())
                     .description(entity.getDescription())
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
        // var us = new UserStory(
        //     jpaEntity.getId(), jpaEntity.getTitle(),
        //     jpaEntity.getDescription(), jpaEntity.getPriority(),
        //     jpaEntity.getStatus(), jpaEntity.getStoryPoints(),
        //     jpaEntity.getAcceptanceCriteria(),
        //     ProductBackLogJpaMapper.toDomainEntity(
        //         jpaEntity.getProductBackLog()),
        //     jpaEntity.getCreatedDate(), jpaEntity.getCreatedBy(),
        //     jpaEntity.getLastModifiedDate(), jpaEntity.getLastModifiedBy());
        //
        // if (jpaEntity.getEpic() != null) {
        //     us.setEpic(EpicJpaMapper.toDomainEntity(jpaEntity.getEpic()));
        // }
        // if (jpaEntity.getSprintBackLog() != null) {
        //     us.setSprintBackLog(SprintBackLogJpaMapper.toDomainEntity(
        //         jpaEntity.getSprintBackLog()));
        // }
        // return us;
        return UserStory.builder()
            .id(jpaEntity.getId())
            .title(jpaEntity.getTitle())
            .description(jpaEntity.getDescription())
            .priority(jpaEntity.getPriority())
            .storyPoints(jpaEntity.getStoryPoints())
            .acceptanceCriteria(jpaEntity.getAcceptanceCriteria())
            // .status(
            //     UserStoryHistoryJpaMapper.toDomainEntity(jpaEntity.getStatus()))
            .productBackLog(ProductBackLogJpaMapper.toDomainEntity(
                jpaEntity.getProductBackLog()))
            .epic(jpaEntity.getEpic() != null
                      ? EpicJpaMapper.toDomainEntity(jpaEntity.getEpic())
                      : null)
            .sprintBackLog(jpaEntity.getSprintBackLog() != null
                               ? SprintBackLogJpaMapper.toDomainEntity(
                                     jpaEntity.getSprintBackLog())
                               : null)
            .createdDate(jpaEntity.getCreatedDate())
            .createdBy(jpaEntity.getCreatedBy())
            .lastModifiedDate(jpaEntity.getLastModifiedDate())
            .lastModifiedBy(jpaEntity.getLastModifiedBy())
            .build();
    }
}
