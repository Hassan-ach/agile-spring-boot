package com.ensa.agile.application.epic.mapper;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.domain.epic.entity.Epic;

public class EpicResponseMapper {

    public static EpicResponse toResponse(Epic epic) {
        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .build();
    }
}
