package com.ensa.agile.infrastructure.persistence.jpa.epic;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.infrastructure.persistence.jpa.product.backlog.ProductBackLogJpaMapper;
import java.util.ArrayList;
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

        // return new Epic(
        //     epic.getId(), epic.getTitle(), epic.getDescription(),
        //     ProductBackLogJpaMapper.toDomainEntity(epic.getProductBackLog()),
        //     epic.getCreatedDate(), epic.getCreatedBy(),
        //     epic.getLastModifiedDate(), epic.getLastModifiedBy());
        return Epic.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .productBackLog(ProductBackLogJpaMapper.toDomainEntity(
                epic.getProductBackLog()))
            .createdDate(epic.getCreatedDate())
            .createdBy(epic.getCreatedBy())
            .lastModifiedDate(epic.getLastModifiedDate())
            .lastModifiedBy(epic.getLastModifiedBy())
            .build();
    }

    public static Epic toDomainEntity(List<EpicRow> rows) {

        if (rows == null || rows.isEmpty()) {
            return null;
        }

        EpicRow firstRow = rows.get(0);
        // Epic resp = new Epic(firstRow.getEpicId(), firstRow.getEpicTitle(),
        //                      firstRow.getEpicDescription());

        Epic resp = Epic.builder()
                        .id(firstRow.getEpicId())
                        .title(firstRow.getEpicTitle())
                        .description(firstRow.getEpicDescription())
                        .userStories(new ArrayList<>())
                        .build();

        for (EpicRow row : rows) {
            if (row.getStoryId() == null) {
                continue;
            }

            // resp.getUserStories().add(new UserStory(
            //     row.getStoryId(), row.getStoryTitle(),
            //     row.getStoryDescription(), row.getPriority(),
            //     row.getStatus(), row.getStoryPoints(),
            //     row.getAcceptanceCriteria()));
            resp.getUserStories().add(
                UserStory.builder()
                    .id(row.getStoryId())
                    .title(row.getStoryTitle())
                    .description(row.getStoryDescription())
                    .priority(row.getPriority())
                    .storyPoints(row.getStoryPoints())
                    .acceptanceCriteria(row.getAcceptanceCriteria())
                    .build());
        }
        return resp;
    }
}
