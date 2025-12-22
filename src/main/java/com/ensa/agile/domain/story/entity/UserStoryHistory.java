package com.ensa.agile.domain.story.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.story.enums.StoryStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserStoryHistory extends BaseDomainEntity {

    private final UserStory userStory;
    private final StoryStatus status;
    private final String note;

    // public UserStoryHistory(UserStory userStory, StoryStatus status,
    //                         String note) {
    //     super(null);
    //     this.userStory = userStory;
    //     this.status = status;
    //     this.note = note;
    // }

    public UserStoryHistory(String id, UserStory userStory, StoryStatus status,
                            String note, LocalDateTime createdDate,
                            String createdBy, LocalDateTime lastModifiedDate,
                            String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.userStory = userStory;
        this.status = status;
        this.note = note;
    }
}
