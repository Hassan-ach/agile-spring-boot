package com.ensa.agile.domain.task.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.task.enums.TaskStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskHistory extends BaseDomainEntity {

        private Task task;

        private TaskStatus status;

        private String note;

        public TaskHistory(Task task, TaskStatus status, String note) {
                super(null);
                this.task = task;
                this.status = status;
                this.note = note;
        }

        public TaskHistory(String id, Task task, TaskStatus status, String note) {
                super(id);
                this.task = task;
                this.status = status;
                this.note = note;
        }
}
