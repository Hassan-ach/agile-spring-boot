package com.ensa.agile.domain.story.entity;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.enums.StoryStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserStory extends BaseDomainEntity {

    private final String title;

    private final String description;
    private final Integer priority;

    private final StoryStatus status;

    private final Integer storyPoints;
    private final String acceptanceCriteria;

    private Epic epic;

    private final ProductBackLog productBackLog;

    private SprintBackLog sprintBackLog;

    public UserStory(String title, String description, Integer priority,
                     StoryStatus status, Integer storyPoints,
                     String acceptanceCriteria, Epic epic,
                     ProductBackLog productBackLog,
                     SprintBackLog sprintBackLog) {
        super(null);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.storyPoints = storyPoints;
        this.acceptanceCriteria = acceptanceCriteria;
        this.epic = epic;
        this.productBackLog = productBackLog;
        this.sprintBackLog = sprintBackLog;
    }
    public UserStory(String id, String title, String description,
                     Integer priority, StoryStatus status, Integer storyPoints,
                     String acceptanceCriteria, ProductBackLog productBackLog,
                     LocalDateTime createdDate, String createdBy,
                     LocalDateTime lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.storyPoints = storyPoints;
        this.acceptanceCriteria = acceptanceCriteria;
        this.productBackLog = productBackLog;
    }

    public UserStory(String id, String title, String description,
                     Integer priority, StoryStatus status, Integer storyPoints,
                     String acceptanceCriteria, Epic epic,
                     ProductBackLog productBackLog, SprintBackLog sprintBackLog,
                     LocalDateTime createdDate, String createdBy,
                     LocalDateTime lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.storyPoints = storyPoints;
        this.acceptanceCriteria = acceptanceCriteria;
        this.epic = epic;
        this.productBackLog = productBackLog;
        this.sprintBackLog = sprintBackLog;
    }
}
