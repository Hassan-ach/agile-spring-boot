package com.ensa.agile.domain.sprint.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.user.entity.User;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class SprintMember extends BaseDomainEntity {
    private final User user;
    private final SprintBackLog sprintBackLog;

    protected SprintMember(SprintMemberBuilder<?, ?> b) {
        super(b);
        this.user = b.user;
        this.sprintBackLog = b.sprintBackLog;

        validate();
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
