package com.ensa.agile.application.sprint.mapper;

import com.ensa.agile.application.sprint.response.SprintBackLogResponse;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import java.util.List;

public class SprintBacklogResponseMapper {

    public static SprintBackLogResponse
    toResponse(SprintBackLog sprint, List<UserStory> userStories) {
        return SprintBackLogResponse.builder()
            .id(sprint.getId())
            .name(sprint.getName())
            .status(sprint.getStatus())
            .startDate(sprint.getStartDate())
            .endDate(sprint.getEndDate())
            .userStories(userStories.stream()
                             .map(UserStoryResponseMapper::toResponse)
                             .toList())
            .build();
    }
}
