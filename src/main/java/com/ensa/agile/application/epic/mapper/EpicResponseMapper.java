package com.ensa.agile.application.epic.mapper;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.epic.entity.Epic;
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
}
