package com.ensa.agile.domain.epic.row;

import com.ensa.agile.domain.story.enums.StoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EpicRow {
    private String epicId;
    private String epicTitle;
    private String epicDescription;

    private String storyId;
    private String storyTitle;
    private String storyDescription;
    private StoryStatus status;
    private Integer priority;
    private Integer storyPoints;
    private String acceptanceCriteria;
}
