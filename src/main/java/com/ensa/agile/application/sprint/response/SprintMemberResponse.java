package com.ensa.agile.application.sprint.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SprintMemberResponse {
    private String id;
    private String userEmail;

    private String invitedBy;
    private LocalDateTime joinedAt;
}
