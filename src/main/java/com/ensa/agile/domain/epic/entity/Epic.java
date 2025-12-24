package com.ensa.agile.domain.epic.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.story.entity.UserStory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Epic extends BaseDomainEntity {

    private String title;
    private String description;
    private ProductBackLog productBackLog;
    private List<UserStory> userStories;

    public Epic(String id, String title, String description,
                ProductBackLog productBackLog, List<UserStory> userStories,
                LocalDateTime createdDate, String createdBy,
                LocalDateTime lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.productBackLog = productBackLog;
        this.userStories = userStories;
    }
}
