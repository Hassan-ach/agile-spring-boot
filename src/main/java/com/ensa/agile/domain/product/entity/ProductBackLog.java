package com.ensa.agile.domain.product.entity;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.story.entity.UserStory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductBackLog extends BaseDomainEntity {

    private String name;

    private String description;

    private List<Epic> epics;

    private List<UserStory> userStories;

    public ProductBackLog(String name, String description) {
        super(null);
        this.name = name;
        this.description = description;
        this.epics = new ArrayList<>();
        this.userStories = new ArrayList<>();
    }

    public ProductBackLog(String name, String description, List<Epic> epics,
                          List<UserStory> userStories) {
        super(null);
        this.name = name;
        this.description = description;
        this.epics = epics;
        this.userStories = userStories;
    }
    public ProductBackLog(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        this.epics = new ArrayList<>();
        this.userStories = new ArrayList<>();
    }

    public ProductBackLog(String id, String name, String description,
                          LocalDateTime createdDate, String createdBy) {
        super(id, createdDate, createdBy, null, null);
        this.name = name;
        this.description = description;
        this.epics = new ArrayList<>();
        this.userStories = new ArrayList<>();
    }

    public ProductBackLog(String id, String name, String description,
                          List<Epic> epics, List<UserStory> userStories,
                          LocalDateTime createdDate, String createdBy,
                          LocalDateTime lastModifiedDate,
                          String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.name = name;
        this.description = description;
        this.epics = epics;
        this.userStories = userStories;
    }
    public ProductBackLog(String id, String name, String description,
                          LocalDateTime createdDate, String createdBy,
                          LocalDateTime lastModifiedDate,
                          String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.name = name;
        this.description = description;
        this.epics = new ArrayList<>();
        this.userStories = new ArrayList<>();
    }
}
