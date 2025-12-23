package com.ensa.agile.domain.task.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
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
}
