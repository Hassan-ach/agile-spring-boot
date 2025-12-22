package com.ensa.agile.application.story.response;

import com.ensa.agile.domain.story.enums.MoscowType;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class UserStoryResponse {
    private final String id;
    private final String title;
    private final String description;
    private final String status;
    private final MoscowType priority;
    private final Integer storyPoints;
    private final String acceptanceCriteria;
}
