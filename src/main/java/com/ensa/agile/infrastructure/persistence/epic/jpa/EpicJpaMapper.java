package com.ensa.agile.infrastructure.persistence.epic.jpa;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaMapper;
import java.util.List;

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

    public static Epic toDomainEntity(List<EpicRow> rows) {

        if (rows == null || rows.isEmpty()) {
            return null;
        }

        EpicRow firstRow = rows.get(0);
        Epic resp = new Epic(firstRow.getEpicId(), firstRow.getEpicTitle(),
                             firstRow.getEpicDescription());

        for (EpicRow row : rows) {
            if (row.getStoryId() == null) {
                continue;
            }

            resp.getUserStories().add(new UserStory(
                row.getStoryId(), row.getStoryTitle(),
                row.getStoryDescription(), row.getPriority(), row.getStatus(),
                row.getStoryPoints(), row.getAcceptanceCriteria()));
        }
        return resp;
    }
}
