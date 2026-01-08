package com.ensa.agile.application.task.request;

import com.ensa.agile.domain.global.utils.ValidationUtil;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.task.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskUpdateRequest {
    private String id;
    private String title;
    private String description;
    private Double estimatedHours;
    private String assigneeEmail;
    private Double actualHours;

    private TaskStatus status;

    private String UserStoryId;
    private String sprintId;

    public TaskUpdateRequest(String sprintId, String taskId,
                             TaskUpdateRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        if (req.title == null && req.description == null &&
            req.estimatedHours == null && req.assigneeEmail == null &&
            req.UserStoryId == null) {
            throw new ValidationException(
                "At least one field must be provided for update");
        }

        this.id = taskId;
        this.sprintId = sprintId;

        if (req.title != null) {
            if (req.title.isBlank()) {
                throw new ValidationException("title cannot be blank");
            } else {
                this.title = req.title;
            }
        }

        if (req.description != null) {
            if (req.description.isBlank()) {
                throw new IllegalArgumentException(
                    "description cannot be blank");
            } else {
                this.description = req.description;
            }
        }

        if (req.estimatedHours != null && req.estimatedHours > 0) {
            this.estimatedHours = req.estimatedHours;
        }
        if (req.actualHours != null && req.actualHours > 0) {
            this.actualHours = req.actualHours;
        }

        if (ValidationUtil.isValidEmail(req.assigneeEmail)) {
            this.assigneeEmail = req.assigneeEmail;
        } else if (req.assigneeEmail != null) {
            throw new ValidationException("Invalid assignee email format");
        }

        if (req.UserStoryId != null) {
            this.UserStoryId = req.UserStoryId;
        }

        if (req.status != null) {
            this.status = req.status;
        }
    }
}
