package com.ensa.agile.infrastructure.persistence.service;

import java.util.ArrayList;
import java.util.List;

public class FetchService {

    public String buildUnionQuery(FetchPlan plan) {
        List<String> queryBlocks = new ArrayList<>();

        // BLOCK 0 - Always include product base (anchor)
        queryBlocks.add(buildProductOnlyBlock());

        // BLOCK 1 - Members
        if (plan.members) {
            queryBlocks.add(buildMembersBlock());
        }

        // BLOCK 2 - Sprints
        if (plan.sprints) {
            queryBlocks.add(buildSprintsBlock());
        }

        // BLOCK 3 - Sprint Members
        if (plan.sprintMembers) {
            queryBlocks.add(buildSprintMembersBlock());
        }

        // BLOCK 4 - Sprint Stories
        if (plan.sprintStories) {
            queryBlocks.add(buildSprintStoriesBlock());
        }

        // BLOCK 5 - Sprint Story Tasks
        if (plan.sprintStoryTasks) {
            queryBlocks.add(buildSprintStoryTasksBlock());
        }

        // BLOCK 6 - Epics
        if (plan.epics) {
            queryBlocks.add(buildEpicsBlock());
        }

        // BLOCK 7 - Epic Stories
        if (plan.epicStories) {
            queryBlocks.add(buildEpicStoriesBlock());
        }

        // BLOCK 8 - Epic Story Tasks (implied by epicStories)
        if (plan.epicStories) {
            queryBlocks.add(buildEpicStoryTasksBlock());
        }

        // BLOCK 9 - Orphan Stories (no epic)
        if (plan.orphanStories) {
            queryBlocks.add(buildOrphanStoriesBlock());
        }

        // BLOCK 10 - Orphan Story Tasks
        if (plan.orphanStoryTasks) {
            queryBlocks.add(buildOrphanStoryTasksBlock());
        }

        // Join all blocks with UNION ALL
        return String.join(" UNION ALL ", queryBlocks);
    }

    /* ============================================================
             BLOCK 0 — PRODUCT ONLY (fallback anchor)
    ============================================================ */
    private String buildProductOnlyBlock() { return """
            SELECT
                p.id AS productId,
                p.name AS productName,
                p.description AS productDescription,
                p.created_by AS productCreatedBy,
                p.created_date AS productCreatedDate,
                p.last_modified_by AS productLastModifiedBy,
                p.last_modified_date AS productLastModifiedDate,
    
                NULL, NULL, NULL, NULL, NULL, NULL, -- project member
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                NULL, NULL, NULL, NULL, -- sprint member
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- user story
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            WHERE p.id = :id
            """; }

    /* ============================================================
            BLOCK 1 — PRODUCT MEMBERS
    ============================================================ */
    private String buildMembersBlock() { return """
            SELECT
                p.id AS productId,
                p.name AS productName,
                p.description AS productDescription,
                p.created_by AS productCreatedBy,
                p.created_date AS productCreatedDate,
                p.last_modified_by AS productLastModifiedBy,
                p.last_modified_date AS productLastModifiedDate,

  pm.id AS projectMemberId,
                u.email AS projectMemberEmail,
                pm.role AS projectMemberRole,
                pm.status AS projectMemberStatus,
                pm.invited_by AS projectMemberInvitedBy,
                pm.invitation_date AS projectMemberInvitationDate,

                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                NULL, NULL, NULL, NULL, -- sprint member
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- user story
                NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN project_members pm ON pm.product_backlog_id = p.id
            LEFT JOIN users u ON u.id = pm.user_id
            WHERE p.id = :id
            """; }

    /* ============================================================
            BLOCK 2 — SPRINT BACKLOGS
    ============================================================ */
    private String buildSprintsBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member

                 s.id AS sprintId,
                 s.name AS sprintName,
                 u.email AS sprintScrumMasterEmail,
                 s.status AS sprintStatus,
                 s.start_date AS sprintStartDate,
                 s.end_date AS sprintEndDate,
                 s.goal AS sprintGoal,
                 s.created_by AS sprintCreatedBy,
                 s.created_date AS sprintCreatedDate,
                 s.last_modified_by AS sprintLastModifiedBy,
                 s.last_modified_date AS sprintLastModifiedDate,
                    
                 sh.id AS sprintHistoryId,
                 sh.status AS sprintHistoryStatus,
                 sh.note AS sprintHistoryNote,

                 NULL, NULL, NULL, NULL, -- sprint member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- user story
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN sprint_backlogs s ON s.product_backlog_id = p.id
            LEFT JOIN sprint_histories sh ON sh.id = (
                     SELECT sh_inner.id FROM sprint_histories sh_inner
                     WHERE sh_inner.sprint_backlog_id = s.id
                     ORDER BY sh_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN users u ON u.id = s.scrum_master_id
            WHERE p.id = :id
            """; }

    /* ============================================================
        BLOCK 3 — SPRINT MEMBERS
    ============================================================ */
    private String buildSprintMembersBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member

                 s.id AS sprintId,
                 s.name AS sprintName,
                 u.email AS sprintScrumMasterEmail,
                 s.status AS sprintStatus,
                 s.start_date AS sprintStartDate,
                 s.end_date AS sprintEndDate,
                 s.goal AS sprintGoal,
                 s.created_by AS sprintCreatedBy,
                 s.created_date AS sprintCreatedDate,
                 s.last_modified_by AS sprintLastModifiedBy,
                 s.last_modified_date AS sprintLastModifiedDate,

                 sm.id AS sprintMemberId,
                 u.email AS sprintMemberEmail,
                 sm.invited_by AS sprintMemberInvitedBy,
                 sm.joined_at AS sprintMemberJoinedAt,

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- user story
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN sprint_backlogs s ON s.product_backlog_id = p.id
            LEFT JOIN sprint_members sm ON sm.sprint_backlog_id = s.id
            LEFT JOIN users u ON u.id = sm.user_id
            WHERE p.id = :id
            """; }
    /* ============================================================
            BLOCK 4 — SPRINT BACKLOGS -> USER STORIES
    ============================================================ */
    private String buildSprintStoriesBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member

                 s.id AS sprintId,
                 s.name AS sprintName,
                 u.email AS sprintScrumMasterEmail,
                 s.status AS sprintStatus,
                 s.start_date AS sprintStartDate,
                 s.end_date AS sprintEndDate,
                 s.goal AS sprintGoal,
                 s.created_by AS sprintCreatedBy,
                 s.created_date AS sprintCreatedDate,
                 s.last_modified_by AS sprintLastModifiedBy,
                 s.last_modified_date AS sprintLastModifiedDate,
                    
                 sh.id AS sprintHistoryId,
                 sh.status AS sprintHistoryStatus,
                 sh.note AS sprintHistoryNote,

                 NULL, NULL, NULL, NULL, -- sprint member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN sprint_backlogs s ON s.product_backlog_id = p.id
            LEFT JOIN sprint_histories sh ON sh.id = (
                     SELECT sh_inner.id FROM sprint_histories sh_inner
                     WHERE sh_inner.sprint_backlog_id = s.id
                     ORDER BY sh_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN users u ON u.id = s.scrum_master_id
            LEFT JOIN user_stories us ON us.sprint_backlog_id = s.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id
            """; }

    /* ============================================================
            BLOCK 5 — SPRINT BACKLOGS -> USER STORIES -> TASKS
    ============================================================ */
    private String buildSprintStoryTasksBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member

                 s.id AS sprintId,
                 s.name AS sprintName,
                 u.email AS sprintScrumMasterEmail,
                 s.status AS sprintStatus,
                 s.start_date AS sprintStartDate,
                 s.end_date AS sprintEndDate,
                 s.goal AS sprintGoal,
                 s.created_by AS sprintCreatedBy,
                 s.created_date AS sprintCreatedDate,
                 s.last_modified_by AS sprintLastModifiedBy,
                 s.last_modified_date AS sprintLastModifiedDate,
                    
                 sh.id AS sprintHistoryId,
                 sh.status AS sprintHistoryStatus,
                 sh.note AS sprintHistoryNote,

                 NULL, NULL, NULL, NULL, -- sprint member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- epic

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 t.id AS taskId,
                 t.title AS taskTitle,
                 t.description AS taskDescription,
                 t.assignee AS taskAssignee,
                 t.estimated_hours AS taskEstimatedHours,
                 t.actual_hours AS taskActualHours,
                 t.created_by AS taskCreatedBy,
                 t.created_date AS taskCreatedDate,
                 t.last_modified_by AS taskLastModifiedBy,
                 t.last_modified_date AS taskLastModifiedDate,

                 th.id AS taskHistoryId,
                 th.status AS taskHistoryStatus,
                 th.note AS taskHistoryNote

            FROM product_backlogs p
            LEFT JOIN sprint_backlogs s ON s.product_backlog_id = p.id
            LEFT JOIN sprint_histories sh ON sh.id = (
                     SELECT sh_inner.id FROM sprint_histories sh_inner
                     WHERE sh_inner.sprint_backlog_id = s.id
                     ORDER BY sh_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN users u ON u.id = s.scrum_master_id
            LEFT JOIN user_stories us ON us.sprint_backlog_id = s.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN tasks t ON t.user_story_id = us.id
            LEFT JOIN task_histories th ON th.id = (
                     SELECT th_inner.id FROM task_histories th_inner
                     WHERE th_inner.task_id = t.id
                     ORDER BY th_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id
            """; }
    /* ============================================================
        BLOCK 6 — EPICS
    ============================================================ */
    private String buildEpicsBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                 NULL, NULL, NULL, NULL, -- sprint member

                 e.id AS epicId,
                 e.title AS epicTitle,
                 e.description AS epicDescription,
                 e.created_by AS epicCreatedBy,
                 e.created_date AS epicCreatedDate,
                 e.last_modified_by AS epicLastModifiedBy,
                 e.last_modified_date AS epicLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- user story
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN epics e ON e.product_backlog_id = p.id
            WHERE p.id = :id
            """; }

    /* ============================================================
            BLOCK 7 — EPIC -> USER STORIES
     ============================================================ */
    private String buildEpicStoriesBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                 NULL, NULL, NULL, NULL, -- sprint member

                 e.id AS epicId,
                 e.title AS epicTitle,
                 e.description AS epicDescription,
                 e.created_by AS epicCreatedBy,
                 e.created_date AS epicCreatedDate,
                 e.last_modified_by AS epicLastModifiedBy,
                 e.last_modified_date AS epicLastModifiedDate,

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN epics e ON e.product_backlog_id = p.id
            LEFT JOIN user_stories us ON us.epic_id = e.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id
            """; }
    /* ============================================================
            BLOCK 8 — EPIC -> USER STORIES -> TASKS
     ============================================================ */
    private String buildEpicStoryTasksBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                 NULL, NULL, NULL, NULL, -- sprint member

                 e.id AS epicId,
                 e.title AS epicTitle,
                 e.description AS epicDescription,
                 e.created_by AS epicCreatedBy,
                 e.created_date AS epicCreatedDate,
                 e.last_modified_by AS epicLastModifiedBy,
                 e.last_modified_date AS epicLastModifiedDate,

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 t.id AS taskId,
                 t.title AS taskTitle,
                 t.description AS taskDescription,
                 t.assignee AS taskAssignee,
                 t.estimated_hours AS taskEstimatedHours,
                 t.actual_hours AS taskActualHours,
                 t.created_by AS taskCreatedBy,
                 t.created_date AS taskCreatedDate,
                 t.last_modified_by AS taskLastModifiedBy,
                 t.last_modified_date AS taskLastModifiedDate,

                 th.id AS taskHistoryId,
                 th.status AS taskHistoryStatus,
                 th.note AS taskHistoryNote

            FROM product_backlogs p
            LEFT JOIN epics e ON e.product_backlog_id = p.id
            LEFT JOIN user_stories us ON us.epic_id = e.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN tasks t ON t.user_story_id = us.id
            LEFT JOIN task_histories th ON th.id = (
                     SELECT th_inner.id FROM task_histories th_inner
                     WHERE th_inner.task_id = t.id
                     ORDER BY th_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id
            """; }

    /* ============================================================
        BLOCK 9 — USER STORIES (no epic)
        ============================================================ */
    private String buildOrphanStoriesBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                 NULL, NULL, NULL, NULL, -- sprint member

                 NULL AS epicId,
                 NULL AS epicTitle,
                 NULL AS epicDescription,
                 NULL AS epicCreatedBy,
                 NULL AS epicCreatedDate,
                 NULL AS epicLastModifiedBy,
                 NULL AS epicLastModifiedDate,

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL -- task
            FROM product_backlogs p
            LEFT JOIN user_stories us ON us.product_backlog_id = p.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id AND us.epic_id IS NULL
            """; }

    /* ============================================================
        BLOCK 10 — USER STORIES (no epic) -> TASKS
        ============================================================ */

    private String buildOrphanStoryTasksBlock() { return """
            SELECT
                 p.id AS productId,
                 p.name AS productName,
                 p.description AS productDescription,
                 p.created_by AS productCreatedBy,
                 p.created_date AS productCreatedDate,
                 p.last_modified_by AS productLastModifiedBy,
                 p.last_modified_date AS productLastModifiedDate,

                 NULL, NULL, NULL, NULL, NULL, NULL, -- project member
                 NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -- sprint backlog
                 NULL, NULL, NULL, NULL, -- sprint member

                 NULL AS epicId,
                 NULL AS epicTitle,
                 NULL AS epicDescription,
                 NULL AS epicCreatedBy,
                 NULL AS epicCreatedDate,
                 NULL AS epicLastModifiedBy,
                 NULL AS epicLastModifiedDate,

                 us.id AS storyId,
                 us.title AS storyTitle,
                 us.description AS storyDescription,
                 us.priority AS priority,
                 us.story_points AS storyPoints,
                 us.acceptance_criteria AS acceptanceCriteria,
                 us.created_by AS storyCreatedBy,
                 us.created_date AS storyCreatedDate,
                 us.last_modified_by AS storyLastModifiedBy,
                 us.last_modified_date AS storyLastModifiedDate,

                 ush.id AS userStoryHistoryId,
                 ush.status AS userStoryHistoryStatus,
                 ush.note AS userStoryHistoryNote,

                 t.id AS taskId,
                 t.title AS taskTitle,
                 t.description AS taskDescription,
                 t.assignee AS taskAssignee,
                 t.estimated_hours AS taskEstimatedHours,
                 t.actual_hours AS taskActualHours,
                 t.created_by AS taskCreatedBy,
                 t.created_date AS taskCreatedDate,
                 t.last_modified_by AS taskLastModifiedBy,
                 t.last_modified_date AS taskLastModifiedDate,

                 th.id AS taskHistoryId,
                 th.status AS taskHistoryStatus,
                 th.note AS taskHistoryNote

            FROM product_backlogs p
            LEFT JOIN user_stories us ON us.product_backlog_id = p.id
            LEFT JOIN user_story_histories ush ON ush.id = (
                     SELECT ush_inner.id FROM user_story_histories ush_inner
                     WHERE ush_inner.user_story_id = us.id
                     ORDER BY ush_inner.created_date DESC LIMIT 1
            )
            LEFT JOIN tasks t ON t.user_story_id = us.id
            LEFT JOIN task_histories th ON th.id = (
                     SELECT th_inner.id FROM task_histories th_inner
                     WHERE th_inner.task_id = t.id
                     ORDER BY th_inner.created_date DESC LIMIT 1
            )
            WHERE p.id = :id AND us.epic_id IS NULL
            """; }
}
