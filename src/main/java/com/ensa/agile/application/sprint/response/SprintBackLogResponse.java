package com.ensa.agile.application.sprint.response;

import com.ensa.agile.domain.sprint.entity.SprintHistory;
import com.ensa.agile.domain.story.entity.UserStory;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SprintBackLogResponse {
    private final String id;
    private final String name;
    private final String scrumMasterEmail;
    private final SprintHistory status;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final List<UserStory> userStories;
}
