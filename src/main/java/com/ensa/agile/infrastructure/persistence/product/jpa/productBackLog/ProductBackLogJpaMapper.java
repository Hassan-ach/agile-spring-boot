package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.story.enums.StoryStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductBackLogJpaMapper {

    public static ProductBackLogJpaEntity
    toJpaEntity(ProductBackLog ProductBackLog) {
        if (ProductBackLog == null) {
            return null;
        }

        ProductBackLogJpaEntity jpaEntity =
            ProductBackLogJpaEntity.builder()
                .id(ProductBackLog.getId())
                .name(ProductBackLog.getName())
                .description(ProductBackLog.getDescription())
                .createdDate(ProductBackLog.getCreatedDate())
                .createdBy(ProductBackLog.getCreatedBy())
                .lastModifiedDate(ProductBackLog.getLastModifiedDate())
                .lastModifiedBy(ProductBackLog.getLastModifiedBy())
                .build();
        return jpaEntity;
    }

    public static ProductBackLog
    toDomainEntity(ProductBackLogJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        return new ProductBackLog(
            jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getDescription(),
            jpaEntity.getCreatedDate(), jpaEntity.getCreatedBy(),
            jpaEntity.getLastModifiedDate(), jpaEntity.getLastModifiedBy());
    }

    public static ProductBackLog toDomainEntity(List<ProductBackLogRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return ProductBackLog.builder().build();
        }

        ProductBackLogRow row0 = rows.get(0);
        ProductBackLog resp =
            new ProductBackLog(row0.getBacklogId(), row0.getBacklogName(),
                               row0.getBacklogDescription(), null, null);
        HashMap<String, Epic> epics = new HashMap<String, Epic>();
        List<UserStory> userStories = new ArrayList<>();

        for (ProductBackLogRow row : rows) {
            if (row.getEpicId() != null) {
                epics.computeIfAbsent(row.getEpicId(),
                                      id
                                      -> new Epic(id, row.getEpicTitle(),
                                                  row.getEpicDescription()));
            }

            if (row.getStoryId() == null)
                continue;

            UserStory us = new UserStory(
                row.getStoryId(), row.getStoryTitle(),
                row.getStoryDescription(), row.getPriority(),
                StoryStatus.valueOf(row.getStatus()), row.getStoryPoints(),
                row.getAcceptanceCriteria());

            if (row.getEpicId() == null) {
                userStories.add(us);
            } else {
                epics.get(row.getEpicId()).getUserStories().add(us);
            }
        }

        resp.setUserStories(userStories);
        resp.setEpics(epics.values().stream().toList());

        return resp;
    }
}
