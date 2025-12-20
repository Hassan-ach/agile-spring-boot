package com.ensa.agile.application.epic.mapper;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.domain.epic.entity.Epic;

public class EpicResponseMapper {

    public static EpicResponse toResponse(Epic epic) {
        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .build();
    }

    public static EpicResponse toResponseWithUserStories(Epic epic) {
        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .userStories(epic.getUserStories()
                             .stream()
                             .map(UserStoryResponseMapper::toResponse)
                             .toList())
            .build();
    }

    // public static EpicResponse toResponse(List<EpicRow> rows) {
    //     if (rows == null || rows.isEmpty()) {
    //         return null;
    //     }
    //
    //     EpicRow firstRow = rows.get(0);
    //     EpicResponse resp = EpicResponse.builder()
    //                             .id(firstRow.getEpicId())
    //                             .title(firstRow.getEpicTitle())
    //                             .description(firstRow.getEpicDescription())
    //                             .userStories(new ArrayList<>())
    //                             .build();
    //
    //     for (EpicRow row : rows) {
    //         if (row.getStoryId() == null) {
    //             continue;
    //         }
    //
    //         resp.getUserStories().add(
    //             UserStoryResponse.builder()
    //                 .id(row.getStoryId())
    //                 .title(row.getStoryTitle())
    //                 .description(row.getStoryDescription())
    //                 .priority(row.getPriority())
    //                 .status(row.getStatus().toString())
    //                 .storyPoints(row.getStoryPoints())
    //                 .acceptanceCriteria(row.getAcceptanceCriteria())
    //                 .build());
    //     }
    //     return null;
    // }
}
