package com.ensa.agile.infrastructure.persistence.service;

import com.ensa.agile.application.epic.request.EpicGetRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.service.IFetchService;
import com.ensa.agile.application.product.request.ProductBackLogGetRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.sprint.request.SprintBackLogGetRequest;
import com.ensa.agile.application.sprint.response.SprintBackLogResponse;
import com.ensa.agile.application.story.request.UserStoryGetRequest;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.infrastructure.persistence.jpa.global.join.Row;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

enum Target { PRODUCT, SPRINT, EPIC, STORY }

@Service
public class FetchService implements IFetchService {

    @PersistenceContext private EntityManager entityManager;

    // --- Template Constants to maintain Column Mapping Integrity ---

    private static final String BASE_SELECT = """
        SELECT p.id AS productId, p.name AS productName, p.description AS productDescription,
        p.created_by AS productCreatedBy, p.created_date AS productCreatedDate,
        p.last_modified_by AS productLastModifiedBy, p.last_modified_date AS productLastModifiedDate
        """;
    private static final String NULL_MEMBER =
        ", CAST(NULL AS VARCHAR) AS projectMemberId, CAST(NULL AS VARCHAR) "
        + "AS projectMemberEmail, CAST(NULL AS VARCHAR) AS projectMemberRole, "
        + "CAST(NULL AS VARCHAR) AS projectMemberStatus, CAST(NULL AS VARCHAR) "
        + "AS projectMemberInvitedBy, CAST(NULL AS TIMESTAMP) AS "
        + "projectMemberInvitationDate";
    private static final String NULL_SPRINT =
        ", CAST(NULL AS VARCHAR) AS sprintId, CAST(NULL AS VARCHAR) AS "
        + "sprintName, CAST(NULL AS VARCHAR) AS sprintScrumMasterEmail, "
        + "CAST(NULL AS DATE) AS sprintStartDate, CAST(NULL AS DATE) AS "
        + "sprintEndDate, CAST(NULL AS VARCHAR) AS sprintGoal, CAST(NULL AS "
        + "VARCHAR) AS sprintCreatedBy, CAST(NULL AS TIMESTAMP) AS "
        + "sprintCreatedDate, CAST(NULL AS VARCHAR) AS sprintLastModifiedBy, "
        + "CAST(NULL AS TIMESTAMP) AS sprintLastModifiedDate, CAST(NULL AS "
        + "VARCHAR) AS sprintHistoryId, CAST(NULL AS VARCHAR) AS "
        + "sprintHistoryStatus, CAST(NULL AS VARCHAR) AS sprintHistoryNote";
    private static final String NULL_SPRINT_MEMBER =
        ", CAST(NULL AS VARCHAR) AS sprintMemberId, CAST(NULL AS VARCHAR) AS "
        + "sprintMemberEmail, CAST(NULL AS VARCHAR) AS sprintMemberInvitedBy, "
        + "CAST(NULL AS TIMESTAMP) AS sprintMemberJoinedAt";
    private static final String NULL_EPIC =
        ", CAST(NULL AS VARCHAR) AS epicId, CAST(NULL AS VARCHAR) AS "
        + "epicTitle, CAST(NULL AS VARCHAR) AS epicDescription, CAST(NULL AS "
        + "VARCHAR) AS epicCreatedBy, CAST(NULL AS TIMESTAMP) AS "
        + "epicCreatedDate, CAST(NULL AS VARCHAR) AS epicLastModifiedBy, "
        + "CAST(NULL AS TIMESTAMP) AS epicLastModifiedDate";
    private static final String NULL_STORY =
        ", CAST(NULL AS VARCHAR) AS storyId, CAST(NULL AS VARCHAR) AS "
        + "storyTitle, CAST(NULL AS VARCHAR) AS storyDescription, CAST(NULL AS "
        + "VARCHAR) AS storyPriority, CAST(NULL AS INTEGER) AS storyPoints, "
        + "CAST(NULL AS VARCHAR) AS storyAcceptanceCriteria, CAST(NULL AS "
        + "VARCHAR) AS storyCreatedBy, CAST(NULL AS TIMESTAMP) AS "
        + "storyCreatedDate, CAST(NULL AS VARCHAR) AS storyLastModifiedBy, "
        + "CAST(NULL AS TIMESTAMP) AS storyLastModifiedDate, CAST(NULL AS "
        + "VARCHAR) AS storyHistoryId, CAST(NULL AS VARCHAR) AS "
        + "storyHistoryStatus, CAST(NULL AS VARCHAR) AS storyHistoryNote";
    private static final String NULL_TASK =
        ", CAST(NULL AS VARCHAR) AS taskId, CAST(NULL AS VARCHAR) AS "
        + "taskTitle, CAST(NULL AS VARCHAR) AS taskDescription, CAST(NULL AS "
        + "VARCHAR) AS taskAssignee, CAST(NULL AS DOUBLE PRECISION) AS "
        + "taskEstimatedHours, CAST(NULL AS DOUBLE PRECISION) AS "
        + "taskActualHours, CAST(NULL AS VARCHAR) AS taskCreatedBy, CAST(NULL "
        + "AS TIMESTAMP) AS taskCreatedDate, CAST(NULL AS VARCHAR) AS "
        + "taskLastModifiedBy, CAST(NULL AS TIMESTAMP) AS "
        + "taskLastModifiedDate, CAST(NULL AS VARCHAR) AS taskHistoryId, "
        + "CAST(NULL AS VARCHAR) AS taskHistoryStatus, CAST(NULL AS VARCHAR) "
        + "AS taskHistoryNote";

    public List<Row> fetch(String id, List<String> fields) {
        FetchPlan plan = FetchPlan.resolve(fields);
        List<Row> results = new ArrayList<>();

        // 1. Always fetch Product Anchor
        results.addAll(execute(id, buildProductAnchorQuery()));

        // 2. Pass: Members
        if (plan.members) {
            results.addAll(execute(id, buildMembersQuery()));
        }

        // 3. Pass: Sprints Branch (Includes Sprint Members, Stories, and Tasks
        // within Sprints)
        if (plan.sprints || plan.sprintMembers || plan.sprintStories ||
            plan.sprintStoryTasks) {
            results.addAll(execute(id, buildSprintDataQuery(plan)));
        }

        // 4. Pass: Epics Branch (Includes Stories and Tasks within Epics)
        if (plan.epics || plan.epicStories || plan.epicStoryTasks) {
            results.addAll(execute(id, buildEpicDataQuery(plan)));
        }

        // 5. Pass: Orphan Stories Branch
        if (plan.orphanStories || plan.orphanStoryTasks) {
            results.addAll(execute(id, buildOrphanStoriesQuery(plan)));
        }

        return results;
    }

    private List<Row> execute(String id, String sql) {
        Query query =
            entityManager.createNativeQuery(sql, "ProductBackLogFetchMapping");
        query.setParameter("id", id);
        return query.getResultList();
    }

    private String buildProductAnchorQuery() {
        return BASE_SELECT + NULL_MEMBER + NULL_SPRINT + NULL_SPRINT_MEMBER +
            NULL_EPIC + NULL_STORY + NULL_TASK +
            " FROM product_backlogs p WHERE p.id = :id";
    }

    private String buildMembersQuery() {
        return BASE_SELECT + """
            , pm.id AS projectMemberId, u.email AS projectMemberEmail, pm.role AS projectMemberRole, 
              pm.status AS projectMemberStatus, pm.created_by AS projectMemberInvitedBy, pm.created_date AS projectMemberInvitationDate
            """ + NULL_SPRINT + NULL_SPRINT_MEMBER + NULL_EPIC +
            NULL_STORY + NULL_TASK + """
            FROM product_backlogs p
            JOIN project_members pm ON pm.product_backlog_id = p.id
            JOIN users u ON u.id = pm.user_id
            WHERE p.id = :id
            """;
    }

    private String buildSprintDataQuery(FetchPlan plan) {
        StringBuilder sql = new StringBuilder(BASE_SELECT + NULL_MEMBER);
        sql.append("""
            , s.id AS sprintId, s.name AS sprintName, u_s.email AS sprintScrumMasterEmail, s.start_date AS sprintStartDate, 
              s.end_date AS sprintEndDate, s.goal AS sprintGoal, s.created_by AS sprintCreatedBy, s.created_date AS sprintCreatedDate, 
              s.last_modified_by AS sprintLastModifiedBy, s.last_modified_date AS sprintLastModifiedDate,
              sh.id AS sprintHistoryId, sh.status AS sprintHistoryStatus, sh.note AS sprintHistoryNote
            """);

        sql.append(plan.sprintMembers
                       ? ", sm.id AS sprintMemberId, u_sm.email AS "
                             + "sprintMemberEmail, sm.created_by AS "
                             + "sprintMemberInvitedBy, sm.created_date AS "
                             + "sprintMemberJoinedAt"
                       : NULL_SPRINT_MEMBER);
        sql.append(NULL_EPIC);
        sql.append(
            plan.sprintStories
                ? ", us.id AS storyId, us.title AS storyTitle, "
                      + "us.description AS storyDescription, us.priority AS "
                      + "storyPriority, us.story_points AS storyPoints, "
                      + "us.acceptance_criteria AS storyAcceptanceCriteria, "
                      + "us.created_by AS storyCreatedBy, us.created_date "
                      + "AS storyCreatedDate, us.last_modified_by AS "
                      + "storyLastModifiedBy, us.last_modified_date AS "
                      + "storyLastModifiedDate, ush.id AS storyHistoryId, "
                      + "ush.status AS storyHistoryStatus, ush.note AS "
                      + "storyHistoryNote"
                : NULL_STORY);
        sql.append(
            plan.sprintStoryTasks
                ? ", t.id AS taskId, t.title AS taskTitle, t.description AS "
                      + "taskDescription, t.assignee_id AS taskAssignee, "
                      +
                      "t.estimated_hours AS taskEstimatedHours, t.actual_hours "
                      + "AS taskActualHours, t.created_by AS taskCreatedBy, "
                      + "t.created_date AS taskCreatedDate, "
                      + "t.last_modified_by AS "
                      + "taskLastModifiedBy, t.last_modified_date AS "
                      +
                      "taskLastModifiedDate, th.id AS taskHistoryId, th.status "
                      + "AS taskHistoryStatus, th.note AS taskHistoryNote"
                : NULL_TASK);

        sql.append(" FROM product_backlogs p JOIN sprint_backlogs s ON "
                   + "s.product_backlog_id = p.id ");
        sql.append(" LEFT JOIN users u_s ON u_s.id = s.scrum_master_id ");
        sql.append(" LEFT JOIN sprint_histories sh ON sh.id = (SELECT id "
                   + "FROM sprint_histories WHERE sprint_backlog_id = s.id "
                   + "ORDER BY created_date DESC LIMIT 1) ");

        if (plan.sprintMembers) {
            sql.append(" LEFT JOIN sprint_members sm ON sm.sprint_backlog_id "
                       +
                       "= s.id LEFT JOIN users u_sm ON u_sm.id = sm.user_id ");
        }
        if (plan.sprintStories) {
            sql.append(
                " LEFT JOIN user_stories us ON us.sprint_backlog_id = s.id ");
            sql.append(
                " LEFT JOIN user_story_histories ush ON ush.id = (SELECT id "
                + "FROM user_story_histories WHERE user_story_id = us.id ORDER "
                + "BY created_date DESC LIMIT 1) ");
            if (plan.sprintStoryTasks) {
                sql.append(" LEFT JOIN tasks t ON t.user_story_id = us.id ");
                sql.append(" LEFT JOIN task_histories th ON th.id = (SELECT "
                           + "id FROM task_histories WHERE task_id = t.id "
                           + "ORDER BY created_date DESC LIMIT 1) ");
            }
        }

        sql.append(" WHERE p.id = :id");
        return sql.toString();
    }

    private String buildEpicDataQuery(FetchPlan plan) {
        StringBuilder sql = new StringBuilder(BASE_SELECT + NULL_MEMBER +
                                              NULL_SPRINT + NULL_SPRINT_MEMBER);
        sql.append(
            ", e.id AS epicId, e.title AS epicTitle, e.description AS "
            + "epicDescription, e.created_by AS epicCreatedBy, e.created_date "
            + "AS epicCreatedDate, e.last_modified_by AS epicLastModifiedBy, "
            + "e.last_modified_date AS epicLastModifiedDate");
        sql.append(
            plan.epicStories
                ? ", us.id AS storyId, us.title AS storyTitle, "
                      + "us.description AS storyDescription, us.priority AS "
                      + "storyPriority, us.story_points AS storyPoints, "
                      + "us.acceptance_criteria AS storyAcceptanceCriteria, "
                      + "us.created_by AS storyCreatedBy, us.created_date "
                      + "AS storyCreatedDate, us.last_modified_by AS "
                      + "storyLastModifiedBy, us.last_modified_date AS "
                      + "storyLastModifiedDate, ush.id AS storyHistoryId, "
                      + "ush.status AS storyHistoryStatus, ush.note AS "
                      + "storyHistoryNote"
                : NULL_STORY);
        sql.append(
            plan.epicStoryTasks
                ? ", t.id AS taskId, t.title AS taskTitle, t.description AS "
                      + "taskDescription, t.assignee_id AS taskAssignee, "
                      +
                      "t.estimated_hours AS taskEstimatedHours, t.actual_hours "
                      + "AS taskActualHours, t.created_by AS taskCreatedBy, "
                      + "t.created_date AS taskCreatedDate, "
                      + "t.last_modified_by AS "
                      + "taskLastModifiedBy, t.last_modified_date AS "
                      +
                      "taskLastModifiedDate, th.id AS taskHistoryId, th.status "
                      + "AS taskHistoryStatus, th.note AS taskHistoryNote"
                : NULL_TASK);

        sql.append(" FROM product_backlogs p JOIN epics e ON "
                   + "e.product_backlog_id = p.id ");
        if (plan.epicStories) {
            sql.append(" LEFT JOIN user_stories us ON us.epic_id = e.id ");
            sql.append(
                " LEFT JOIN user_story_histories ush ON ush.id = (SELECT id "
                + "FROM user_story_histories WHERE user_story_id = us.id ORDER "
                + "BY created_date DESC LIMIT 1) ");
            if (plan.epicStoryTasks) {
                sql.append(" LEFT JOIN tasks t ON t.user_story_id = us.id ");
                sql.append(" LEFT JOIN task_histories th ON th.id = (SELECT "
                           + "id FROM task_histories WHERE task_id = t.id "
                           + "ORDER BY created_date DESC LIMIT 1) ");
            }
        }
        sql.append(" WHERE p.id = :id");
        return sql.toString();
    }

    private String buildOrphanStoriesQuery(FetchPlan plan) {
        StringBuilder sql =
            new StringBuilder(BASE_SELECT + NULL_MEMBER + NULL_SPRINT +
                              NULL_SPRINT_MEMBER + NULL_EPIC);
        sql.append(
            ", us.id AS storyId, us.title AS storyTitle, us.description AS "
            + "storyDescription, us.priority AS storyPriority, us.story_points "
            + "AS storyPoints, us.acceptance_criteria AS "
            + "storyAcceptanceCriteria, us.created_by AS storyCreatedBy, "
            + "us.created_date AS storyCreatedDate, us.last_modified_by AS "
            + "storyLastModifiedBy, us.last_modified_date AS "
            + "storyLastModifiedDate, ush.id AS storyHistoryId, ush.status AS "
            + "storyHistoryStatus, ush.note AS storyHistoryNote");
        sql.append(
            plan.orphanStoryTasks
                ? ", t.id AS taskId, t.title AS taskTitle, t.description AS "
                      + "taskDescription, t.assignee_id AS taskAssignee, "
                      +
                      "t.estimated_hours AS taskEstimatedHours, t.actual_hours "
                      + "AS taskActualHours, t.created_by AS taskCreatedBy, "
                      + "t.created_date AS taskCreatedDate, "
                      + "t.last_modified_by AS "
                      + "taskLastModifiedBy, t.last_modified_date AS "
                      +
                      "taskLastModifiedDate, th.id AS taskHistoryId, th.status "
                      + "AS taskHistoryStatus, th.note AS taskHistoryNote"
                : NULL_TASK);

        sql.append(" FROM product_backlogs p JOIN user_stories us ON "
                   + "us.product_backlog_id = p.id AND us.epic_id IS NULL AND "
                   + "us.sprint_backlog_id IS NULL ");
        sql.append(" LEFT JOIN user_story_histories ush ON ush.id = (SELECT "
                   + "id FROM user_story_histories WHERE user_story_id = us.id "
                   + "ORDER BY created_date DESC LIMIT 1) ");
        if (plan.orphanStoryTasks) {
            sql.append(" LEFT JOIN tasks t ON t.user_story_id = us.id ");
            sql.append(" LEFT JOIN task_histories th ON th.id = (SELECT id "
                       + "FROM task_histories WHERE task_id = t.id ORDER BY "
                       + "created_date DESC LIMIT 1) ");
        }
        sql.append(" WHERE p.id = :id");
        return sql.toString();
    }

    @Override
    public ProductBackLogResponse getResponse(ProductBackLogGetRequest req) {
        return Transformer.transform(this.fetch(req.getId(), req.getFields()));
    }

    @Override
    public SprintBackLogResponse getResponse(SprintBackLogGetRequest req) {
        return null;
    }

    @Override
    public EpicResponse getResponse(EpicGetRequest req) {
        return null;
    }

    @Override
    public UserStoryResponse getResponse(UserStoryGetRequest req) {
        return null;
    }
}
