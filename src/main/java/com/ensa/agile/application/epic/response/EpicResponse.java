package com.ensa.agile.application.epic.response;

import com.ensa.agile.application.story.response.UserStoryResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EpicResponse {
    private String id;
    private final String title;
    private final String description;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    private List<UserStoryResponse> userStories;
}
