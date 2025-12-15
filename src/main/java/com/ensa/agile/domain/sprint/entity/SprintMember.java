package com.ensa.agile.domain.sprint.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SprintMember extends BaseDomainEntity {
    private final User user;
    private final SprintBackLog sprintBackLog;

    public SprintMember(User user, SprintBackLog sprintBacklog) {
        super(null);
        this.user = user;
        this.sprintBackLog = sprintBacklog;
    }

    public SprintMember(String id, User user, SprintBackLog sprint,
                        LocalDateTime createdDate, String createdBy,
                        LocalDateTime lastModifiedDate, String lastModifiedBy) {

        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.user = user;
        this.sprintBackLog = sprint;
    }
}
