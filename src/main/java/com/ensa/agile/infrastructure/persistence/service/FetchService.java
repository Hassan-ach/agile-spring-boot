package com.ensa.agile.infrastructure.persistence.service;

import com.ensa.agile.application.global.service.IFetchService;
import com.ensa.agile.application.product.request.ProductBackLogGetRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.infrastructure.persistence.global.join.Row;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class FetchService implements IFetchService {

    @PersistenceContext private EntityManager entityManager;

    public List<Row> fetch(String id, List<String> fields) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        String sql = this.buildUnionQuery(FetchPlan.resolve(fields));

        Query query =
            entityManager.createNativeQuery(sql, "ProductBackLogFetchMapping");

        params.forEach(query::setParameter);

        List<Row> result = query.getResultList();
        return result;
    }

    @Override
    public ProductBackLogResponse getResponse(ProductBackLogGetRequest req) {
        return Transformer.transform(this.fetch(req.getId(), req.getFields()));
    }

    private String buildUnionQuery(FetchPlan plan) {
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
        if (plan.epicStoryTasks) {
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
            -- PRODUCT BACKLOG
            p.id                         AS productId,
            p.name                       AS productName,
            p.description                AS productDescription,
            p.created_by                 AS productCreatedBy,
            p.created_date               AS productCreatedDate,
            p.last_modified_by           AS productLastModifiedBy,
            p.last_modified_date         AS productLastModifiedDate,
        
            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,
        
            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,
        
            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,
        
            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,
        
            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,
        
            -- USER STORY
            CAST(NULL AS VARCHAR)        AS storyId,
            CAST(NULL AS VARCHAR)        AS storyTitle,
            CAST(NULL AS VARCHAR)        AS storyDescription,
            CAST(NULL AS VARCHAR)        AS storyPriority,
            CAST(NULL AS INTEGER)        AS storyPoints,
            CAST(NULL AS VARCHAR)        AS storyAcceptanceCriteria,
            CAST(NULL AS VARCHAR)        AS storyCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS storyCreatedDate,
            CAST(NULL AS VARCHAR)        AS storyLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS storyLastModifiedDate,
        
            -- USER STORY HISTORY
            CAST(NULL AS VARCHAR)        AS storyHistoryId,
            CAST(NULL AS VARCHAR)        AS storyHistoryStatus,
            CAST(NULL AS VARCHAR)        AS storyHistoryNote,
        
            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,
        
            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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
            pm.created_by AS projectMemberInvitedBy,
            pm.created_date AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,
        
            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,
        
            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,
        
            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,
        
            -- USER STORY
            CAST(NULL AS VARCHAR)        AS storyId,
            CAST(NULL AS VARCHAR)        AS storyTitle,
            CAST(NULL AS VARCHAR)        AS storyDescription,
            CAST(NULL AS VARCHAR)        AS storyPriority,
            CAST(NULL AS INTEGER)        AS storyPoints,
            CAST(NULL AS VARCHAR)        AS storyAcceptanceCriteria,
            CAST(NULL AS VARCHAR)        AS storyCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS storyCreatedDate,
            CAST(NULL AS VARCHAR)        AS storyLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS storyLastModifiedDate,
        
            -- USER STORY HISTORY
            CAST(NULL AS VARCHAR)        AS storyHistoryId,
            CAST(NULL AS VARCHAR)        AS storyHistoryStatus,
            CAST(NULL AS VARCHAR)        AS storyHistoryNote,
        
            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,
        
            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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

             -- PROJECT MEMBER
             CAST(NULL AS VARCHAR)        AS projectMemberId,
             CAST(NULL AS VARCHAR)        AS projectMemberEmail,
             CAST(NULL AS VARCHAR)        AS projectMemberRole,
             CAST(NULL AS VARCHAR)        AS projectMemberStatus,
             CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
             CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

             s.id AS sprintId,
             s.name AS sprintName,
             u.email AS sprintScrumMasterEmail,
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

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,

            -- USER STORY
            CAST(NULL AS VARCHAR)        AS storyId,
            CAST(NULL AS VARCHAR)        AS storyTitle,
            CAST(NULL AS VARCHAR)        AS storyDescription,
            CAST(NULL AS VARCHAR)        AS storyPriority,
            CAST(NULL AS INTEGER)        AS storyPoints,
            CAST(NULL AS VARCHAR)        AS storyAcceptanceCriteria,
            CAST(NULL AS VARCHAR)        AS storyCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS storyCreatedDate,
            CAST(NULL AS VARCHAR)        AS storyLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS storyLastModifiedDate,

            -- USER STORY HISTORY
            CAST(NULL AS VARCHAR)        AS storyHistoryId,
            CAST(NULL AS VARCHAR)        AS storyHistoryStatus,
            CAST(NULL AS VARCHAR)        AS storyHistoryNote,

            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote

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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            s.id AS sprintId,
            s.name AS sprintName,
            scu.email AS sprintScrumMasterEmail,
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

            sm.id AS sprintMemberId,
            smu.email AS sprintMemberEmail,
            sm.created_by AS sprintMemberInvitedBy,
            sm.created_date AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,

            -- USER STORY
            CAST(NULL AS VARCHAR)        AS storyId,
            CAST(NULL AS VARCHAR)        AS storyTitle,
            CAST(NULL AS VARCHAR)        AS storyDescription,
            CAST(NULL AS VARCHAR)        AS storyPriority,
            CAST(NULL AS INTEGER)        AS storyPoints,
            CAST(NULL AS VARCHAR)        AS storyAcceptanceCriteria,
            CAST(NULL AS VARCHAR)        AS storyCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS storyCreatedDate,
            CAST(NULL AS VARCHAR)        AS storyLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS storyLastModifiedDate,

            -- USER STORY HISTORY
            CAST(NULL AS VARCHAR)        AS storyHistoryId,
            CAST(NULL AS VARCHAR)        AS storyHistoryStatus,
            CAST(NULL AS VARCHAR)        AS storyHistoryNote,

            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote

        FROM product_backlogs p
        LEFT JOIN sprint_backlogs s ON s.product_backlog_id = p.id
        LEFT JOIN sprint_histories sh ON sh.id = (
                 SELECT sh_inner.id FROM sprint_histories sh_inner
                 WHERE sh_inner.sprint_backlog_id = s.id
                 ORDER BY sh_inner.created_date DESC LIMIT 1
        )
        LEFT JOIN sprint_members sm ON sm.sprint_backlog_id = s.id
        LEFT JOIN users smu ON smu.id = sm.user_id
        LEFT JOIN users scu ON scu.id = s.scrum_master_id

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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            s.id AS sprintId,
            s.name AS sprintName,
            u.email AS sprintScrumMasterEmail,
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

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,

            us.id AS storyId,
            us.title AS storyTitle,
            us.description AS storyDescription,
            us.priority AS storyPriority,
            us.story_points AS storyPoints,
            us.acceptance_criteria AS storyAcceptanceCriteria,
            us.created_by AS storyCreatedBy,
            us.created_date AS storyCreatedDate,
            us.last_modified_by AS storyLastModifiedBy,
            us.last_modified_date AS storyLastModifiedDate,

            ush.id AS storyHistoryId,
            ush.status AS storyHistoryStatus,
            ush.note AS storyHistoryNote,

        
            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            s.id AS sprintId,
            s.name AS sprintName,
            u.email AS sprintScrumMasterEmail,
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
         
            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,

            us.id AS storyId,
            us.title AS storyTitle,
            us.description AS storyDescription,
            us.priority AS storyPriority,
            us.story_points AS storyPoints,
            us.acceptance_criteria AS storyAcceptanceCriteria,
            us.created_by AS storyCreatedBy,
            us.created_date AS storyCreatedDate,
            us.last_modified_by AS storyLastModifiedBy,
            us.last_modified_date AS storyLastModifiedDate,

            ush.id AS storyHistoryId,
            ush.status AS storyHistoryStatus,
            ush.note AS storyHistoryNote,

            t.id AS taskId,
            t.title AS taskTitle,
            t.description AS taskDescription,
            t.assignee_id AS taskAssignee,
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,

            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

            e.id AS epicId,
            e.title AS epicTitle,
            e.description AS epicDescription,
            e.created_by AS epicCreatedBy,
            e.created_date AS epicCreatedDate,
            e.last_modified_by AS epicLastModifiedBy,
            e.last_modified_date AS epicLastModifiedDate,

            -- USER STORY
            CAST(NULL AS VARCHAR)        AS storyId,
            CAST(NULL AS VARCHAR)        AS storyTitle,
            CAST(NULL AS VARCHAR)        AS storyDescription,
            CAST(NULL AS VARCHAR)        AS storyPriority,
            CAST(NULL AS INTEGER)        AS storyPoints,
            CAST(NULL AS VARCHAR)        AS storyAcceptanceCriteria,
            CAST(NULL AS VARCHAR)        AS storyCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS storyCreatedDate,
            CAST(NULL AS VARCHAR)        AS storyLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS storyLastModifiedDate,

            -- USER STORY HISTORY
            CAST(NULL AS VARCHAR)        AS storyHistoryId,
            CAST(NULL AS VARCHAR)        AS storyHistoryStatus,
            CAST(NULL AS VARCHAR)        AS storyHistoryNote,

            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
						 CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,

            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

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
            us.priority AS storyPriority,
            us.story_points AS storyPoints,
            us.acceptance_criteria AS storyAcceptanceCriteria,
            us.created_by AS storyCreatedBy,
            us.created_date AS storyCreatedDate,
            us.last_modified_by AS storyLastModifiedBy,
            us.last_modified_date AS storyLastModifiedDate,

            ush.id AS storyHistoryId,
            ush.status AS storyHistoryStatus,
            ush.note AS storyHistoryNote,


        
            -- TASK
            CAST(NULL AS VARCHAR)        AS taskId,
            CAST(NULL AS VARCHAR)        AS taskTitle,
            CAST(NULL AS VARCHAR)        AS taskDescription,
            CAST(NULL AS VARCHAR)        AS taskAssignee,
			CAST(NULL AS DOUBLE PRECISION)         AS taskEstimatedHours,
			CAST(NULL AS DOUBLE PRECISION)         AS taskActualHours,
            CAST(NULL AS VARCHAR)        AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS taskCreatedDate,
            CAST(NULL AS VARCHAR)        AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,

            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

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
            us.priority AS storyPriority,
            us.story_points AS storyPoints,
            us.acceptance_criteria AS storyAcceptanceCriteria,
            us.created_by AS storyCreatedBy,
            us.created_date AS storyCreatedDate,
            us.last_modified_by AS storyLastModifiedBy,
            us.last_modified_date AS storyLastModifiedDate,

            ush.id AS storyHistoryId,
            ush.status AS storyHistoryStatus,
            ush.note AS storyHistoryNote,

            t.id AS taskId,
            t.title AS taskTitle,
            t.description AS taskDescription,
            t.assignee_id AS taskAssignee,
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)          AS projectMemberId,
            CAST(NULL AS VARCHAR)          AS projectMemberEmail,
            CAST(NULL AS VARCHAR)          AS projectMemberRole,
            CAST(NULL AS VARCHAR)          AS projectMemberStatus,
            CAST(NULL AS VARCHAR)          AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)        AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)          AS sprintId,
            CAST(NULL AS VARCHAR)          AS sprintName,
            CAST(NULL AS VARCHAR)          AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)             AS sprintStartDate,
            CAST(NULL AS DATE)             AS sprintEndDate,
            CAST(NULL AS VARCHAR)          AS sprintGoal,
            CAST(NULL AS VARCHAR)          AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)        AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)          AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)        AS sprintLastModifiedDate,

            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)          AS sprintHistoryId,
            CAST(NULL AS VARCHAR)          AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)          AS sprintHistoryNote,

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)          AS sprintMemberId,
            CAST(NULL AS VARCHAR)          AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)          AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)        AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)          AS epicId,
            CAST(NULL AS VARCHAR)          AS epicTitle,
            CAST(NULL AS VARCHAR)          AS epicDescription,
            CAST(NULL AS VARCHAR)          AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)        AS epicCreatedDate,
            CAST(NULL AS VARCHAR)          AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)        AS epicLastModifiedDate,

            us.id                          AS storyId,
            us.title                       AS storyTitle,
            us.description                 AS storyDescription,
            us.priority                    AS storyPriority,
            us.story_points                AS storyPoints,
            us.acceptance_criteria         AS storyAcceptanceCriteria,
            us.created_by                  AS storyCreatedBy,
            us.created_date                AS storyCreatedDate,
            us.last_modified_by            AS storyLastModifiedBy,
            us.last_modified_date          AS storyLastModifiedDate,

            ush.id                         AS storyHistoryId,
            ush.status                     AS storyHistoryStatus,
            ush.note                       AS storyHistoryNote,

            -- TASK
            CAST(NULL AS VARCHAR)          AS taskId,
            CAST(NULL AS VARCHAR)          AS taskTitle,
            CAST(NULL AS VARCHAR)          AS taskDescription,
            CAST(NULL AS VARCHAR)          AS taskAssignee,
			CAST(NULL AS DOUBLE PRECISION) AS taskEstimatedHours,
			CAST(NULL AS DOUBLE PRECISION) AS taskActualHours,
            CAST(NULL AS VARCHAR)          AS taskCreatedBy,
            CAST(NULL AS TIMESTAMP)        AS taskCreatedDate,
            CAST(NULL AS VARCHAR)          AS taskLastModifiedBy,
            CAST(NULL AS TIMESTAMP)        AS taskLastModifiedDate,

            -- TASK HISTORY
            CAST(NULL AS VARCHAR)        AS taskHistoryId,
            CAST(NULL AS VARCHAR)        AS taskHistoryStatus,
            CAST(NULL AS VARCHAR)        AS taskHistoryNote
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

            -- PROJECT MEMBER
            CAST(NULL AS VARCHAR)        AS projectMemberId,
            CAST(NULL AS VARCHAR)        AS projectMemberEmail,
            CAST(NULL AS VARCHAR)        AS projectMemberRole,
            CAST(NULL AS VARCHAR)        AS projectMemberStatus,
            CAST(NULL AS VARCHAR)        AS projectMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS projectMemberInvitationDate,

            -- SPRINT
            CAST(NULL AS VARCHAR)        AS sprintId,
            CAST(NULL AS VARCHAR)        AS sprintName,
            CAST(NULL AS VARCHAR)        AS sprintScrumMasterEmail,
            CAST(NULL AS DATE)           AS sprintStartDate,
            CAST(NULL AS DATE)           AS sprintEndDate,
            CAST(NULL AS VARCHAR)        AS sprintGoal,
            CAST(NULL AS VARCHAR)        AS sprintCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintCreatedDate,
            CAST(NULL AS VARCHAR)        AS sprintLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintLastModifiedDate,

            -- SPRINT HISTORY
            CAST(NULL AS VARCHAR)        AS sprintHistoryId,
            CAST(NULL AS VARCHAR)        AS sprintHistoryStatus,
            CAST(NULL AS VARCHAR)        AS sprintHistoryNote,

            -- SPRINT MEMBER
            CAST(NULL AS VARCHAR)        AS sprintMemberId,
            CAST(NULL AS VARCHAR)        AS sprintMemberEmail,
            CAST(NULL AS VARCHAR)        AS sprintMemberInvitedBy,
            CAST(NULL AS TIMESTAMP)      AS sprintMemberJoinedAt,

            -- EPIC
            CAST(NULL AS VARCHAR)        AS epicId,
            CAST(NULL AS VARCHAR)        AS epicTitle,
            CAST(NULL AS VARCHAR)        AS epicDescription,
            CAST(NULL AS VARCHAR)        AS epicCreatedBy,
            CAST(NULL AS TIMESTAMP)      AS epicCreatedDate,
            CAST(NULL AS VARCHAR)        AS epicLastModifiedBy,
            CAST(NULL AS TIMESTAMP)      AS epicLastModifiedDate,

            us.id AS storyId,
            us.title AS storyTitle,
            us.description AS storyDescription,
            us.priority AS storyPriority,
            us.story_points AS storyPoints,
            us.acceptance_criteria AS storyAcceptanceCriteria,
            us.created_by AS storyCreatedBy,
            us.created_date AS storyCreatedDate,
            us.last_modified_by AS storyLastModifiedBy,
            us.last_modified_date AS storyLastModifiedDate,

            ush.id AS storyHistoryId,
            ush.status AS storyHistoryStatus,
            ush.note AS storyHistoryNote,

            t.id AS taskId,
            t.title AS taskTitle,
            t.description AS taskDescription,
            t.assignee_id AS taskAssignee,
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
