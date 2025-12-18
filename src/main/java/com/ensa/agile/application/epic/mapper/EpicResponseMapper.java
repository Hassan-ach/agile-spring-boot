package com.ensa.agile.application.epic.mapper;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.row.EpicRow;
import java.util.ArrayList;
import java.util.List;

public class EpicResponseMapper {

    public static EpicResponse toResponse(Epic epic) {
        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .build();
    }

    public static EpicResponse toResponse(Epic epic,
                                          List<UserStoryResponse> userStories) {
        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .userStories(userStories)
            .build();
    }

    public static EpicResponse toResponse(List<EpicRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return null;
        }

        EpicRow firstRow = rows.get(0);
        EpicResponse resp = EpicResponse.builder()
                                .id(firstRow.getEpicId())
                                .title(firstRow.getEpicTitle())
                                .description(firstRow.getEpicDescription())
                                .userStories(new ArrayList<>())
                                .build();

        for (EpicRow row : rows) {
            if (row.getStoryId() == null) {
                continue;
            }

            resp.getUserStories().add(
                UserStoryResponse.builder()
                    .id(row.getStoryId())
                    .title(row.getStoryTitle())
                    .description(row.getStoryDescription())
                    .priority(row.getPriority())
                    .status(row.getStatus())
                    .storyPoints(row.getStoryPoints())
                    .acceptanceCriteria(row.getAcceptanceCriteria())
                    .build());
        }
        return null;
    }
}
