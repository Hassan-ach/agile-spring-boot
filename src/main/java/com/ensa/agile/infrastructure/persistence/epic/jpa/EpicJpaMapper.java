package com.ensa.agile.infrastructure.persistence.epic.jpa;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaMapper;

public class EpicJpaMapper {
    public static EpicJpaEntity toJpaEntity(Epic epic) {

        return EpicJpaEntity.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .productBackLog(
                ProductBackLogJpaMapper.toJpaEntity(epic.getProductBackLog()))
            .createdDate(epic.getCreatedDate())
            .createdBy(epic.getCreatedBy())
            .lastModifiedDate(epic.getLastModifiedDate())
            .lastModifiedBy(epic.getLastModifiedBy())
            .build();
    }

    public static Epic toDomainEntity(EpicJpaEntity epic) {

        return new Epic(
            epic.getId(), epic.getTitle(), epic.getDescription(),
            ProductBackLogJpaMapper.toDomainEntity(epic.getProductBackLog()),
            epic.getCreatedDate(), epic.getCreatedBy(),
            epic.getLastModifiedDate(), epic.getLastModifiedBy());
    }
}
