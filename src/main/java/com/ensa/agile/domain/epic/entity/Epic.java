package com.ensa.agile.domain.epic.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
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

    public void updateMetadata(String title, String description) {
        if (title != null) {
            this.title = title;
        }
        if (description != null) {
            this.description = description;
        }
        this.validate();
    }

    public void validate() {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Epic title cannot be null or empty");
        }

        if (description == null || description.isEmpty()) {
            throw new ValidationException(
                "Epic description cannot be null or empty");
        }

        if (productBackLog == null) {
            throw new ValidationException(
                "Epic must be associated with a Product Backlog");
        }
    }
}
