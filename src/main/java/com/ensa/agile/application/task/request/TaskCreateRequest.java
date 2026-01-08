package com.ensa.agile.application.task.request;

import com.ensa.agile.domain.global.utils.ValidationUtil;
import com.ensa.agile.domain.global.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskCreateRequest {
    private String title;
    private String description;
    private Double estimatedHours;
    private String assigneeEmail;

    private String UserStoryId;
    private String sprintId;

    public TaskCreateRequest(String sprintId, TaskCreateRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new ValidationException("title cannot be null or blank");
        }

        if (req.getDescription() == null || req.getDescription().isBlank()) {
            throw new ValidationException(
                "description cannot be null or blank");
        }
        if (req.getUserStoryId() == null || req.getUserStoryId().isBlank()) {
            throw new ValidationException(
                "UserStoryId cannot be null or blank");
        }
        if (req.getEstimatedHours() == null || req.getEstimatedHours() <= 0) {
            throw new ValidationException(
                "estimatedHours must be greater than zero");
        }

        if (req.getAssigneeEmail() == null ||
            !ValidationUtil.isValidEmail(req.getAssigneeEmail())) {
            throw new ValidationException("Invalid assignee email format");
        }

        this.title = req.getTitle();
        this.description = req.getDescription();
        this.estimatedHours = req.getEstimatedHours();
        this.assigneeEmail = req.getAssigneeEmail();
        this.UserStoryId = req.getUserStoryId();
        this.sprintId = sprintId;
    }
}
