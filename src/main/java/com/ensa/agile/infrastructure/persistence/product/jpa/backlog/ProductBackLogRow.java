package com.ensa.agile.infrastructure.persistence.product.jpa.backlog;

import com.ensa.agile.domain.story.enums.MoscowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
class ProductBackLogRow {
    String backlogId;
    String backlogName;
    String backlogDescription;

    String epicId;
    String epicTitle;
    String epicDescription;

    String storyId;
    String storyTitle;
    String storyDescription;
    MoscowType priority;
    Integer storyPoints;
    String acceptanceCriteria;

    public ProductBackLogRow(String backlogId, String backlogName,
                             String backlogDescription, String epicId,
                             String epicTitle, String epicDescription,
                             String storyId, String storyTitle,
                             String storyDescription, String priority,
                             Integer storyPoints, String acceptanceCriteria) {
        this.backlogId = backlogId;
        this.backlogName = backlogName;
        this.backlogDescription = backlogDescription;
        this.epicId = epicId;
        this.epicTitle = epicTitle;
        this.epicDescription = epicDescription;
        this.storyId = storyId;
        this.storyTitle = storyTitle;
        this.storyDescription = storyDescription;
        this.priority = priority != null ? MoscowType.valueOf(priority) : null;
        this.storyPoints = storyPoints;
        this.acceptanceCriteria = acceptanceCriteria;
    }
}
