package com.ensa.agile.domain.product.entity;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
public class ProductBackLog extends BaseDomainEntity {

    private String name;
    private String description;
    private List<Epic> epics;
    private List<UserStory> userStories;
    private List<ProjectMember> projectMembers;
    private List<SprintBackLog> sprintBackLogs;

    public ProductBackLog(String id, String name, String description,
                          List<Epic> epics, List<UserStory> userStories,
                          List<ProjectMember> projectMembers,
                          List<SprintBackLog> sprintBackLogs,
                          LocalDateTime createdDate, String createdBy,
                          LocalDateTime lastModifiedDate,
                          String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.name = name;
        this.description = description;
        this.epics = epics;
        this.userStories = userStories;
        this.projectMembers = projectMembers;
        this.sprintBackLogs = sprintBackLogs;
    }
    // public ProductBackLog(String id, String name, String description,
    //                       LocalDateTime createdDate, String createdBy,
    //                       LocalDateTime lastModifiedDate,
    //                       String lastModifiedBy) {
    //     super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    //     this.name = name;
    //     this.description = description;
    //     this.epics = new ArrayList<>();
    //     this.userStories = new ArrayList<>();
    //     this.projectMembers = new ArrayList<>();
    //     this.sprintBackLogs = new ArrayList<>();
    // }
}
