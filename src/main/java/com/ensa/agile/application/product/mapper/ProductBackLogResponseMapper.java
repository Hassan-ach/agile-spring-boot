package com.ensa.agile.application.product.mapper;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.row.ProductBackLogRow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductBackLogResponseMapper {

    public static ProductBackLogResponse
    tResponse(ProductBackLog productBackLog) {
        return ProductBackLogResponse.builder()
            .id(productBackLog.getId())
            .name(productBackLog.getName())
            .description(productBackLog.getDescription())
            .build();
    }

    public static ProductBackLogResponse
    toResponseWithEpics(ProductBackLog productBackLog,
                        List<EpicResponse> epics) {
        return ProductBackLogResponse.builder()
            .id(productBackLog.getId())
            .name(productBackLog.getName())
            .description(productBackLog.getDescription())
            .epics(epics)
            .build();
    }
    public static ProductBackLogResponse
    tLogResponseWithUserStories(ProductBackLog productBackLog,
                                List<UserStoryResponse> UserStories) {
        return ProductBackLogResponse.builder()
            .id(productBackLog.getId())
            .name(productBackLog.getName())
            .description(productBackLog.getDescription())
            .userStories(UserStories)
            .build();
    }

    public static ProductBackLogResponse
    tLogResponse(List<ProductBackLogRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return ProductBackLogResponse.builder().build();
        }

        ProductBackLogRow row0 = rows.get(0);
        ProductBackLogResponse resp =
            ProductBackLogResponse.builder()
                .id(row0.getBacklogId())
                .name(row0.getBacklogName())
                .description(row0.getBacklogDescription())
                .build();

        HashMap<String, EpicResponse> epics =
            new HashMap<String, EpicResponse>();
        List<UserStoryResponse> userStories = new ArrayList<>();

        for (ProductBackLogRow row : rows) {
            if (row.getEpicId() != null) {
                epics.computeIfAbsent(
                    row.getEpicId(),
                    id
                    -> EpicResponse.builder()
                           .id(id)
                           .title(row.getEpicTitle())
                           .description(row.getEpicDescription())
                           .userStories(new ArrayList<>())
                           .build());
            }

            if (row.getStoryId() == null)
                continue;

            UserStoryResponse us =
                UserStoryResponse.builder()
                    .id(row.getStoryId())
                    .title(row.getStoryTitle())
                    .description(row.getStoryDescription())
                    .status(row.getStatus())
                    .priority(row.getPriority())
                    .storyPoints(row.getStoryPoints())
                    .acceptanceCriteria(row.getAcceptanceCriteria())
                    .build();

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
