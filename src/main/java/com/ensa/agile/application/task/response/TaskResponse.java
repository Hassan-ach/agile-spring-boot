package com.ensa.agile.application.task.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private String assignee;
    private Double estimatedHours;
    private TaskHistoryResponse status;
    private Double actualHours;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
