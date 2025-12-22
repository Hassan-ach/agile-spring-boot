package com.ensa.agile.domain.story.entity;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
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

    private final String title;
    private final String description;
    private final MoscowType priority;
    private final Integer storyPoints;
    private final String acceptanceCriteria;
    private Epic epic;
    private ProductBackLog productBackLog;
    private SprintBackLog sprintBackLog;
    private List<Task> tasks;
    private List<UserStoryHistory> userStoryHistories;
    private UserStoryHistory status;

    // public UserStory(String title, String description, MoscowType priority,
    //                  Integer storyPoints, String acceptanceCriteria, Epic
    //                  epic, ProductBackLog productBackLog, SprintBackLog
    //                  sprintBackLog) {
    //     super(null, null, null, null, null);
    //     this.title = title;
    //     this.description = description;
    //     this.priority = priority;
    //     this.storyPoints = storyPoints;
    //     this.acceptanceCriteria = acceptanceCriteria;
    //     this.epic = epic;
    //     this.productBackLog = productBackLog;
    //     this.sprintBackLog = sprintBackLog;
    //     this.tasks = new ArrayList<>();
    //     this.userStoryHistories = new ArrayList<>();
    // }
    //
    // public UserStory(String title, String description, MoscowType priority,
    //                  Integer storyPoints, String acceptanceCriteria, Epic
    //                  epic, ProductBackLog productBackLog, SprintBackLog
    //                  sprintBackLog, List<Task> tasks, List<UserStoryHistory>
    //                  userStoryHistories) {
    //     super(null, null, null, null, null);
    //     this.title = title;
    //     this.description = description;
    //     this.priority = priority;
    //     this.storyPoints = storyPoints;
    //     this.acceptanceCriteria = acceptanceCriteria;
    //     this.epic = epic;
    //     this.productBackLog = productBackLog;
    //     this.sprintBackLog = sprintBackLog;
    //     this.tasks = tasks;
    //     this.userStoryHistories = userStoryHistories;
    // }
    // public UserStory(String id, String title, String description,
    //                  MoscowType priority, Integer storyPoints,
    //                  String acceptanceCriteria, ProductBackLog
    //                  productBackLog, LocalDateTime createdDate, String
    //                  createdBy, LocalDateTime lastModifiedDate, String
    //                  lastModifiedBy, List<Task> tasks, List<UserStoryHistory>
    //                  userStoryHistories) {
    //     super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    //     this.title = title;
    //     this.description = description;
    //     this.priority = priority;
    //     this.storyPoints = storyPoints;
    //     this.acceptanceCriteria = acceptanceCriteria;
    //     this.productBackLog = productBackLog;
    //     this.tasks = tasks;
    //     this.userStoryHistories = userStoryHistories;
    // }
    // public UserStory(String id, String title, String description,
    //                  MoscowType priority, StoryStatus status,
    //                  Integer storyPoints, String acceptanceCriteria,
    //                  List<Task> tasks,
    //                  List<UserStoryHistory> userStoryHistories) {
    //     super(id);
    //     this.title = title;
    //     this.description = description;
    //     this.priority = priority;
    //     this.storyPoints = storyPoints;
    //     this.acceptanceCriteria = acceptanceCriteria;
    //     this.tasks = tasks;
    //     this.userStoryHistories = userStoryHistories;
    // }
    // public UserStory(String id, String title, String description,
    //                  MoscowType priority, Integer storyPoints,
    //                  String acceptanceCriteria, List<Task> tasks,
    //                  UserStoryHistory status) {
    //     super(null);
    //     this.title = title;
    //     this.description = description;
    //     this.priority = priority;
    //     this.storyPoints = storyPoints;
    //     this.acceptanceCriteria = acceptanceCriteria;
    //     this.tasks = tasks;
    //     this.status = status;
    // }

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
}
