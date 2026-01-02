package com.ensa.agile.application.story.response;

import com.ensa.agile.domain.story.enums.StoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserStoryHistoryResponse {

    private String statusId;
    private StoryStatus status;
    private String note;
}
