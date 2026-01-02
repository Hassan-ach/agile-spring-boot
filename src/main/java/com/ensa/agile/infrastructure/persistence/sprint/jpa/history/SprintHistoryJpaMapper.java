package com.ensa.agile.infrastructure.persistence.sprint.jpa.history;

import com.ensa.agile.domain.sprint.entity.SprintHistory;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.backlog.SprintBackLogJpaMapper;

public class SprintHistoryJpaMapper {
    public static SprintHistoryJpaEntity toJpaEntity(SprintHistory domain) {
        return SprintHistoryJpaEntity.builder()
            .id(domain.getId())
            .sprint(SprintBackLogJpaMapper.toJpaEntity(domain.getSprint()))
            .status(domain.getStatus())
            .note(domain.getNote())
            .createdBy(domain.getCreatedBy())
            .createdDate(domain.getCreatedDate())
            .lastModifiedBy(domain.getLastModifiedBy())
            .lastModifiedDate(domain.getLastModifiedDate())
            .build();
    }

    public static SprintHistory
    toDomainEntity(SprintHistoryJpaEntity jpaEntity) {
        return SprintHistory.builder()
            .id(jpaEntity.getId())
            .sprint(
                SprintBackLogJpaMapper.toDomainEntity(jpaEntity.getSprint()))
            .status(jpaEntity.getStatus())
            .note(jpaEntity.getNote())
            .createdBy(jpaEntity.getCreatedBy())
            .createdDate(jpaEntity.getCreatedDate())
            .lastModifiedBy(jpaEntity.getLastModifiedBy())
            .lastModifiedDate(jpaEntity.getLastModifiedDate())
            .build();
    }
}
