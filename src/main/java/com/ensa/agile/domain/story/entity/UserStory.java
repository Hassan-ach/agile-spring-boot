package com.ensa.agile.domain.story.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.entity.Epic;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.sprint.entity.SprintBackLog;
import com.ensa.agile.domain.story.enums.StoryStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserStory extends BaseDomainEntity {

                private final String title;

                private final String description;
                private final Integer priority;

                private final StoryStatus status;

                private final Integer storyPoints;
                private final String acceptanceCriteria;

                private final Epic epic;

                private final ProductBackLog productBackLog;

                private final SprintBackLog sprintBackLog;

                public UserStory(String title, String description, Integer priority, StoryStatus status,
                                                Integer storyPoints,
                                                String acceptanceCriteria, Epic epic, ProductBackLog productBackLog,
                                                SprintBackLog sprintBackLog) {
                                super(null);
                                this.title = title;
                                this.description = description;
                                this.priority = priority;
                                this.status = status;
                                this.storyPoints = storyPoints;
                                this.acceptanceCriteria = acceptanceCriteria;
                                this.epic = epic;
                                this.productBackLog = productBackLog;
                                this.sprintBackLog = sprintBackLog;
                }

                public UserStory(String id, String title, String description, Integer priority, StoryStatus status,
                                                Integer storyPoints, String acceptanceCriteria, Epic epic,
                                                ProductBackLog productBackLog,
                                                SprintBackLog sprintBackLog, LocalDate createdDate, String createdBy,
                                                LocalDate lastModifiedDate, String lastModifiedBy) {
                                super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
                                this.title = title;
                                this.description = description;
                                this.priority = priority;
                                this.status = status;
                                this.storyPoints = storyPoints;
                                this.acceptanceCriteria = acceptanceCriteria;
                                this.epic = epic;
                                this.productBackLog = productBackLog;
                                this.sprintBackLog = sprintBackLog;
                }
}
