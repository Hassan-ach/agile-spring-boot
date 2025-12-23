package com.ensa.agile.domain.sprint.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class SprintBackLog extends BaseDomainEntity {

    private final String name;
    private final ProductBackLog productBackLog;
    private final User scrumMaster;
    private List<SprintMember> members;
    private List<UserStory> userStories;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String goal;
    private SprintHistory status;
    private List<SprintHistory> sprintHistories;

    public SprintBackLog(String id, String name, ProductBackLog productBackLog,
                         User scrumMaster, List<SprintMember> sprintMembers,
                         List<UserStory> userStories, LocalDate startDate,
                         LocalDate endDate, String goal, SprintHistory status,
                         List<SprintHistory> sprintHistories,
                         LocalDateTime createdDate, String createdBy,
                         LocalDateTime lastModifiedDate,
                         String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.name = name;
        this.productBackLog = productBackLog;
        this.scrumMaster = scrumMaster;
        this.members = sprintMembers;
        this.userStories = userStories;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goal = goal;
        this.status = status;
        this.sprintHistories = sprintHistories;
    }
}
