package com.ensa.agile.domain.story.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.story.enums.StoryStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserStoryHistory extends BaseDomainEntity {

    private final UserStory userStory;

    private final StoryStatus status;

    private final String note;

    public UserStoryHistory(UserStory userStory, StoryStatus status, String note) {
        super(null);
        this.userStory = userStory;
        this.status = status;
        this.note = note;
    }

    public UserStoryHistory(String id, UserStory userStory, StoryStatus status, String note, LocalDate createdDate,
            String createdBy,
            LocalDate lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.userStory = userStory;
        this.status = status;
        this.note = note;
    }
}
