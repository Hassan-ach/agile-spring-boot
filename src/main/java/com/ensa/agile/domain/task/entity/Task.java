package com.ensa.agile.domain.task.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.story.entity.UserStory;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Task extends BaseDomainEntity {

        private String title;

        private String description;

        private UserStory userStory;

        private User assignee;
        private Double estimatedHours;
        private Double actualHours;

        public Task(String title, String description, UserStory userStory, User assignee, Double estimatedHours,
                        Double actualHours) {
                super(null);
                this.title = title;
                this.description = description;
                this.userStory = userStory;
                this.assignee = assignee;
                this.estimatedHours = estimatedHours;
                this.actualHours = actualHours;
        }

        public Task(String id, String title, String description, UserStory userStory, User assignee,
                        Double estimatedHours, Double actualHours, LocalDate createdDate, String createdBy,
                        LocalDate lastModifiedDate, String lastModifiedBy) {
                super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
                this.title = title;
                this.description = description;
                this.userStory = userStory;
                this.assignee = assignee;
                this.estimatedHours = estimatedHours;
                this.actualHours = actualHours;
        }
}
