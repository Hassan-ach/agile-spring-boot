package com.ensa.agile.application.task.response;

import com.ensa.agile.domain.task.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskHistoryResponse {
    private String statusId;
    private TaskStatus status;
    private String note;
}
