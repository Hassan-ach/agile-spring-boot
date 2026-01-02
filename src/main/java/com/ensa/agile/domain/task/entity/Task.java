package com.ensa.agile.domain.task.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Task extends BaseDomainEntity {

    private String title;
    private String description;
    private UserStory userStory;
    private SprintBackLog sprintBackLog;
    private User assignee;
    private Double estimatedHours;
    private Double actualHours;
    private List<TaskHistory> taskHistories;
    private TaskHistory status;

    public Task(String id, String title, String description,
                UserStory userStory, SprintBackLog sprintBackLog, User assignee,
                Double estimatedHours, Double actualHours,
                List<TaskHistory> taskHistories, TaskHistory status,
                LocalDateTime createdDate, String createdBy,
                LocalDateTime lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.userStory = userStory;
        this.sprintBackLog = sprintBackLog;
        this.assignee = assignee;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.taskHistories = taskHistories;
        this.status = status;
    }
    public void validate() {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new ValidationException(
                "Description cannot be null or empty");
        }
        if (estimatedHours != null && estimatedHours < 0) {
            throw new ValidationException("Estimated hours cannot be negative");
        }
        if (actualHours != null && actualHours < 0) {
            throw new ValidationException("Actual hours cannot be negative");
        }
    }
    public void updateMetadata(String title, String description,
                               Double estimatedHours, Double actualHours) {
        if (title != null) {
            this.title = title;
        }
        if (description != null) {
            this.description = description;
        }
        if (estimatedHours != null) {
            this.estimatedHours = estimatedHours;
        }
        if (actualHours != null) {
            this.actualHours = actualHours;
        }
        validate();
    }
}
