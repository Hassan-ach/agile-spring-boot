package com.ensa.agile.application.story.response;

import com.ensa.agile.application.task.response.TaskResponse;
import com.ensa.agile.domain.story.entity.UserStoryHistory;
import com.ensa.agile.domain.story.enums.MoscowType;
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
public class UserStoryResponse {
    private String id;
    private String title;
    private String description;
    private MoscowType priority;
    private Integer storyPoints;
    private String acceptanceCriteria;
    private UserStoryHistory status;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    private List<TaskResponse> tasks;
}
