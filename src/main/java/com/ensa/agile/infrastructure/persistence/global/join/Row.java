package com.ensa.agile.infrastructure.persistence.global.join;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Row {

    /* ===================== PRODUCT BACKLOG ===================== */
    private String productId;
    private String productName;
    private String productDescription;
    private String productCreatedBy;
    private LocalDateTime productCreatedDate;
    private String productLastModifiedBy;
    private LocalDateTime productLastModifiedDate;

    /* ===================== PROJECT MEMBER ===================== */
    private String projectMemberId;
    private String projectMemberEmail;
    private String projectMemberRole;
    private String projectMemberStatus;
    private String projectMemberInvitedBy;
    private LocalDateTime projectMemberInvitationDate;

    /* ===================== SPRINT BACKLOG ===================== */
    private String sprintId;
    private String sprintName;
    private String sprintScrumMasterEmail;
    // private String sprintStatus;
    private LocalDate sprintStartDate;
    private LocalDate sprintEndDate;
    private String sprintGoal;
    private String sprintCreatedBy;
    private LocalDateTime sprintCreatedDate;
    private String sprintLastModifiedBy;
    private LocalDateTime sprintLastModifiedDate;

    /* ===================== SPRINT HISTORY ===================== */
    private String sprintHistoryId;
    private String sprintHistoryStatus;
    private String sprintHistoryNote;

    /* ===================== SPRINT MEMBER ===================== */
    private String sprintMemberId;
    private String sprintMemberEmail;
    private String sprintMemberInvitedBy;
    private LocalDateTime sprintMemberJoinedAt;

    /* ===================== EPIC ===================== */
    private String epicId;
    private String epicTitle;
    private String epicDescription;
    private String epicCreatedBy;
    private LocalDateTime epicCreatedDate;
    private String epicLastModifiedBy;
    private LocalDateTime epicLastModifiedDate;

    /* ===================== USER STORY ===================== */
    private String storyId;
    private String storyTitle;
    private String storyDescription;
    private String storyPriority; // MoscowType as String
    private Integer storyPoints;
    private String storyAcceptanceCriteria;
    private String storyCreatedBy;
    private LocalDateTime storyCreatedDate;
    private String storyLastModifiedBy;
    private LocalDateTime storyLastModifiedDate;

    /* ===================== USER STORY HISTORY ===================== */
    private String storyHistoryId;
    private String storyHistoryStatus;
    private String storyHistoryNote;

    /* ===================== TASK ===================== */
    private String taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskAssignee;
    private Double taskEstimatedHours;
    private Double taskActualHours;
    private String taskCreatedBy;
    private LocalDateTime taskCreatedDate;
    private String taskLastModifiedBy;
    private LocalDateTime taskLastModifiedDate;

    /* ===================== TASK HISTORY ===================== */
    private String taskHistoryId;
    private String taskHistoryStatus;
    private String taskHistoryNote;
}
