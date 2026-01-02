package com.ensa.agile.infrastructure.persistence.task.jpa.task;

import com.ensa.agile.domain.task.entity.Task;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.backlog.SprintBackLogJpaMapper;
import com.ensa.agile.infrastructure.persistence.story.jpa.userstory.UserStoryJpaMapper;
import com.ensa.agile.infrastructure.persistence.task.jpa.history.TaskHistoryJpaEntity;
import com.ensa.agile.infrastructure.persistence.task.jpa.history.TaskHistoryJpaMapper;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaMapper;
import java.util.List;

public class TaskJpaMapper {

    public static TaskJpaEntity toJpaEntity(Task task) {
        if (task == null) {
            return null;
        }
        return TaskJpaEntity.builder()
            .id(task.getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .userStory(UserStoryJpaMapper.toJpaEntity(task.getUserStory()))
            .sprintBackLog(
                SprintBackLogJpaMapper.toJpaEntity(task.getSprintBackLog()))
            .assignee(UserJpaMapper.toJpaEntity(task.getAssignee()))
            .estimatedHours(task.getEstimatedHours())
            .actualHours(task.getActualHours())
            .status(TaskHistoryJpaMapper.toJpaEntity(task.getStatus()))
            .createdBy(task.getCreatedBy())
            .createdDate(task.getCreatedDate())
            .lastModifiedBy(task.getLastModifiedBy())
            .lastModifiedDate(task.getLastModifiedDate())
            .build();
    }

    public static Task toDomainEntity(TaskJpaEntity taskJpaEntity) {
        if (taskJpaEntity == null) {
            return null;
        }
        return Task.builder()
            .id(taskJpaEntity.getId())
            .title(taskJpaEntity.getTitle())
            .description(taskJpaEntity.getDescription())
            .userStory(
                UserStoryJpaMapper.toDomainEntity(taskJpaEntity.getUserStory()))
            .sprintBackLog(SprintBackLogJpaMapper.toDomainEntity(
                taskJpaEntity.getSprintBackLog()))
            .assignee(UserJpaMapper.toDomainEntity(taskJpaEntity.getAssignee()))
            .estimatedHours(taskJpaEntity.getEstimatedHours())
            .actualHours(taskJpaEntity.getActualHours())
            .status(
                TaskHistoryJpaMapper.toDomainEntity(taskJpaEntity.getStatus()))
            .createdBy(taskJpaEntity.getCreatedBy())
            .createdDate(taskJpaEntity.getCreatedDate())
            .lastModifiedBy(taskJpaEntity.getLastModifiedBy())
            .lastModifiedDate(taskJpaEntity.getLastModifiedDate())
            .build();
    }

    public static Task toDomainEntity(TaskJpaEntity task,
                                      List<TaskHistoryJpaEntity> histories) {
        Task domainTask = toDomainEntity(task);
        domainTask.setTaskHistories(
            histories.stream()
                .map(TaskHistoryJpaMapper::toDomainEntity)
                .toList());
        return domainTask;
    }
}
