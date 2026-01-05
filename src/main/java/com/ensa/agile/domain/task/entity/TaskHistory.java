package com.ensa.agile.domain.task.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.task.enums.TaskStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TaskHistory extends BaseDomainEntity {

    private final Task task;
    private final TaskStatus status;
    private final String note;

    public TaskHistory(String id, Task task, TaskStatus status, String note,
                       LocalDateTime createdDate, String createdBy,
                       LocalDateTime lastModifiedDate, String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.task = task;
        this.status = status;
        this.note = note;
    }
}
