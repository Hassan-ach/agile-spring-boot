package com.ensa.agile.domain.sprint.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class SprintMember extends BaseDomainEntity {
    private final User user;
    private final SprintBackLog sprintBackLog;

    public SprintMember(String id, User user, SprintBackLog sprint,
                        LocalDateTime createdDate, String createdBy,
                        LocalDateTime lastModifiedDate, String lastModifiedBy) {

        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.user = user;
        this.sprintBackLog = sprint;
    }

    public void validate() {
        if (this.user == null) {
            throw new ValidationException(
                "Sprint member must be associated with a user.");
        }
        if (this.sprintBackLog == null) {
            throw new ValidationException(
                "Sprint member must be associated with a sprint backlog.");
        }
    }
}
