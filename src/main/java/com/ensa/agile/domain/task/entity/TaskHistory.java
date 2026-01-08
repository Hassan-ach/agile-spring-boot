package com.ensa.agile.domain.task.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.task.enums.TaskStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TaskHistory extends BaseDomainEntity {

    private final Task task;
    private final TaskStatus status;
    private final String note;

    protected TaskHistory(TaskHistoryBuilder<?, ?> b) {
        super(b);
        this.task = b.task;
        this.status = b.status;
        this.note = b.note;
        validate();
    }

    public void validate() {
        if (this.task == null) {
            throw new ValidationException(
                "TaskHistory must be associated with a Task.");
        }
        if (this.status == null) {
            throw new ValidationException(
                "TaskHistory must have a valid status.");
        }
        if (this.note != null && this.note.length() > 500) {
            throw new ValidationException(
                "TaskHistory note cannot exceed 500 characters.");
        }
    }
}
