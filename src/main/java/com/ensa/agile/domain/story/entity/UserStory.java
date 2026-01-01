package com.ensa.agile.domain.story.entity;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.enums.MoscowType;
import com.ensa.agile.domain.task.entity.Task;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class UserStory extends BaseDomainEntity {

    private String title;
    private String description;
    private MoscowType priority;
    private Integer storyPoints;
    private String acceptanceCriteria;
    private Epic epic;
    private ProductBackLog productBackLog;
    private SprintBackLog sprintBackLog;
    private List<Task> tasks;
    private List<UserStoryHistory> userStoryHistories;
    private UserStoryHistory status;

    public UserStory(String id, String title, String description,
                     MoscowType priority, Integer storyPoints,
                     String acceptanceCriteria, Epic epic,
                     ProductBackLog productBackLog, SprintBackLog sprintBackLog,
                     List<Task> tasks,
                     List<UserStoryHistory> userStoryHistories,
                     UserStoryHistory status, LocalDateTime createdDate,
                     String createdBy, LocalDateTime lastModifiedDate,
                     String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.acceptanceCriteria = acceptanceCriteria;
        this.epic = epic;
        this.productBackLog = productBackLog;
        this.sprintBackLog = sprintBackLog;
        this.tasks = tasks;
        this.userStoryHistories = userStoryHistories;
        this.status = status;
    }

    public void validate() {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new ValidationException("Title cannot be null or empty");
        }
        if (description == null || description.isEmpty() ||
            description.isBlank()) {
            throw new ValidationException(
                "Description cannot be null or empty");
        }
        if (priority == null) {
            throw new ValidationException("Priority cannot be null");
        }
        if (storyPoints == null || storyPoints < 0 || storyPoints > 100) {
            throw new ValidationException(
                "Story points cannot be null or negative");
        }
        if (acceptanceCriteria == null || acceptanceCriteria.isEmpty() ||
            acceptanceCriteria.isBlank()) {
            throw new ValidationException(
                "Acceptance criteria cannot be null or empty");
        }
    }

    public void updateMetaData(String title, String description,
                               MoscowType priority, Integer storyPoints,
                               String acceptanceCriteria) {
        if (title != null)
            this.title = title;
        if (description != null)
            this.description = description;
        if (priority != null)
            this.priority = priority;
        if (storyPoints != null)
            this.storyPoints = storyPoints;
        if (acceptanceCriteria != null)
            this.acceptanceCriteria = acceptanceCriteria;
    }
}
