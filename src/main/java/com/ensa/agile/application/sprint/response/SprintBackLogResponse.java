package com.ensa.agile.application.sprint.response;

import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.sprint.entity.SprintHistory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SprintBackLogResponse {
    private String id;
    private String name;
    private String scrumMasterEmail;
    private SprintHistory status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String goal;
    private List<SprintHistory> sprintHistories;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    private List<SprintMemberResponse> members;
    private List<UserStoryResponse> userStories;
}
