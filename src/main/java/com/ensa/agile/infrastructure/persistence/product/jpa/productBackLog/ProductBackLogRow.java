package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogRow {
    String backlogId;
    String backlogName;
    String backlogDescription;

    String epicId;
    String epicTitle;
    String epicDescription;

    String storyId;
    String storyTitle;
    String storyDescription;
    String status;
    Integer priority;
    Integer storyPoints;
    String acceptanceCriteria;
}
