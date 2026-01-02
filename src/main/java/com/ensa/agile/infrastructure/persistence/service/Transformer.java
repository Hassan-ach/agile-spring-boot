package com.ensa.agile.infrastructure.persistence.service;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.product.response.ProjectMemberResponse;
import com.ensa.agile.application.sprint.response.SprintBackLogResponse;
import com.ensa.agile.application.sprint.response.SprintMemberResponse;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.application.task.response.TaskResponse;
import com.ensa.agile.domain.product.enums.MemberStatus;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.sprint.entity.SprintHistory;
import com.ensa.agile.domain.sprint.enums.SprintStatus;
import com.ensa.agile.domain.story.entity.UserStoryHistory;
import com.ensa.agile.domain.story.enums.MoscowType;
import com.ensa.agile.domain.story.enums.StoryStatus;
import com.ensa.agile.domain.task.entity.TaskHistory;
import com.ensa.agile.domain.task.enums.TaskStatus;
import com.ensa.agile.infrastructure.persistence.global.join.Row;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transformer {

    public static ProductBackLogResponse transform(List<Row> rows) {
        if (rows == null || rows.isEmpty())
            return null;

        // 1. Root Object (Backlog)
        Row firstRow = rows.get(0);
        ProductBackLogResponse response = mapBacklog(firstRow);

        // 2. Identity Maps to handle nesting and prevent duplicates
        Map<String, ProjectMemberResponse> membersMap = new LinkedHashMap<>();
        Map<String, SprintBackLogResponse> sprintsMap = new LinkedHashMap<>();
        Map<String, EpicResponse> epicsMap = new LinkedHashMap<>();
        Map<String, UserStoryResponse> storiesMap = new LinkedHashMap<>();
        // Tracks which stories belong to which Epics or the Root
        Map<String, List<UserStoryResponse>> epicStories = new HashMap<>();

        for (Row row : rows) {

            // Process Project Member
            if (row.getProjectMemberId() != null) {
                membersMap.computeIfAbsent(row.getProjectMemberId(),
                                           id -> mapMember(row));
            }

            // Process Sprint
            if (row.getSprintId() != null) {
                sprintsMap.computeIfAbsent(row.getSprintId(),
                                           id -> mapSprint(row));
                // Add Sprint Member if present
                if (row.getSprintMemberId() != null) {
                    sprintsMap.get(row.getSprintId())
                        .getMembers()
                        .add(mapSprintMember(row));
                }
            }

            // Process Epic
            if (row.getEpicId() != null) {
                epicsMap.computeIfAbsent(row.getEpicId(), id -> mapEpic(row));
            }

            // Process User Story
            if (row.getStoryId() != null) {
                UserStoryResponse story = storiesMap.computeIfAbsent(
                    row.getStoryId(), id -> mapStory(row));

                // Link Story to Epic if applicable
                if (row.getEpicId() != null) {
                    epicStories
                        .computeIfAbsent(row.getEpicId(),
                                         k -> new ArrayList<>())
                        .add(story);
                }

                // Process Task
                if (row.getTaskId() != null) {
                    story.getTasks().add(mapTask(row));
                }
            }
        }

        // 3. Assemble the Hierarchy
        response.setProjectMembers(new ArrayList<>(membersMap.values()));
        response.setSprintBackLogs(new ArrayList<>(sprintsMap.values()));

        // Attach Stories to Epics
        epicsMap.forEach((epicId, epic) -> {
            epic.setUserStories(
                epicStories.getOrDefault(epicId, new ArrayList<>()));
        });
        response.setEpics(new ArrayList<>(epicsMap.values()));

        // Attach Orphan Stories (Stories with no Epic)
        response.setUserStories(
            storiesMap.values()
                .stream()
                .filter(s
                        -> rows.stream().anyMatch(
                            r
                            -> r.getStoryId().equals(s.getId()) &&
                                   r.getEpicId() == null))
                .collect(Collectors.toList()));

        return response;
    }

    private static ProductBackLogResponse mapBacklog(Row row) {
        return ProductBackLogResponse.builder()
            .id(row.getProductId())
            .name(row.getProductName())
            .description(row.getProductDescription())
            .createdBy(row.getProductCreatedBy())
            .createdDate(row.getProductCreatedDate())
            .lastModifiedBy(row.getProductLastModifiedBy())
            .lastModifiedDate(row.getProductLastModifiedDate())
            .build();
    }

    private static ProjectMemberResponse mapMember(Row row) {
        ProjectMemberResponse member =
            ProjectMemberResponse.builder()
                .memberId(row.getProjectMemberId())
                .userEmail(row.getProjectMemberEmail())
                .role(RoleType.valueOf(row.getProjectMemberRole()))
                .status(MemberStatus.valueOf(row.getProjectMemberStatus()))
                .invitedBy(row.getProjectMemberInvitedBy())
                .invitationDate(row.getProjectMemberInvitationDate())
                .build();
        return member;
    }

    private static SprintBackLogResponse mapSprint(Row row) {
        SprintBackLogResponse sprint = new SprintBackLogResponse();
        sprint.setId(row.getSprintId());
        sprint.setName(row.getSprintName());
        sprint.setScrumMasterEmail(row.getSprintScrumMasterEmail());
        sprint.setStartDate(row.getSprintStartDate());
        sprint.setEndDate(row.getSprintEndDate());
        sprint.setGoal(row.getSprintGoal());
        sprint.setCreatedBy(row.getSprintCreatedBy());
        sprint.setCreatedDate(row.getSprintCreatedDate());

        // Map Sprint History (Latest)
        if (row.getSprintHistoryId() != null) {
            // SprintHistoryResponse history = new SprintHistoryResponse();
            // history.setStatusId(row.getSprintHistoryId());
            // history.setStatus(
            //     SprintStatus.valueOf(row.getSprintHistoryStatus()));
            // history.setNote(row.getSprintHistoryNote());
            // sprint.setStatus(history);
            sprint.setStatus(
                SprintHistory.builder()
                    .id(row.getSprintHistoryId())
                    .status(SprintStatus.valueOf(row.getSprintHistoryStatus()))
                    .note(row.getSprintHistoryNote())
                    .build());
        }

        sprint.setMembers(new ArrayList<>());
        sprint.setUserStories(new ArrayList<>());
        return sprint;
    }

    private static SprintMemberResponse mapSprintMember(Row row) {
        SprintMemberResponse sm = new SprintMemberResponse();
        sm.setId(row.getSprintMemberId());
        sm.setUserEmail(row.getSprintMemberEmail());
        sm.setInvitedBy(row.getSprintMemberInvitedBy());
        sm.setJoinedAt(row.getSprintMemberJoinedAt());
        return sm;
    }

    private static EpicResponse mapEpic(Row row) {
        EpicResponse epic = EpicResponse.builder()
                                .id(row.getEpicId())
                                .title(row.getEpicTitle())
                                .description(row.getEpicDescription())
                                .createdBy(row.getEpicCreatedBy())
                                .createdDate(row.getEpicCreatedDate())
                                .userStories(new ArrayList<>())
                                .build();
        return epic;
    }

    private static UserStoryResponse mapStory(Row row) {
        UserStoryResponse story = new UserStoryResponse();
        story.setId(row.getStoryId());
        story.setTitle(row.getStoryTitle());
        story.setDescription(row.getStoryDescription());
        story.setPriority(MoscowType.valueOf(row.getStoryPriority()));
        story.setStoryPoints(row.getStoryPoints());
        story.setAcceptanceCriteria(row.getStoryAcceptanceCriteria());
        story.setCreatedBy(row.getStoryCreatedBy());
        story.setCreatedDate(row.getStoryCreatedDate());

        // Map Story History (Latest)
        if (row.getStoryHistoryId() != null) {
            // UserStoryHistoryResponse history = new
            // UserStoryHistoryResponse();
            // history.setStatusId(row.getStoryHistoryId());
            // history.setStatus(StoryStatus.valueOf(row.getStoryHistoryStatus()));
            // history.setNote(row.getStoryHistoryNote());
            // story.setStatus(history);
            story.setStatus(
                UserStoryHistory.builder()
                    .id(row.getStoryHistoryId())
                    .status(StoryStatus.valueOf(row.getStoryHistoryStatus()))
                    .note(row.getStoryHistoryNote())
                    .build());
        }

        story.setTasks(new ArrayList<>());
        return story;
    }

    private static TaskResponse mapTask(Row row) {
        TaskResponse task = new TaskResponse();
        task.setId(row.getTaskId());
        task.setTitle(row.getTaskTitle());
        task.setDescription(row.getTaskDescription());
        task.setAssignee(row.getTaskAssignee());
        task.setEstimatedHours(row.getTaskEstimatedHours());
        task.setActualHours(row.getTaskActualHours());
        task.setCreatedBy(row.getTaskCreatedBy());
        task.setCreatedDate(row.getTaskCreatedDate());

        // Map Task History (Latest)
        if (row.getTaskHistoryId() != null) {
            // TaskHistoryResponse history = new TaskHistoryResponse();
            // history.setStatusId(row.getTaskHistoryId());
            // history.setStatus(TaskStatus.valueOf(row.getTaskHistoryStatus()));
            // history.setNote(row.getTaskHistoryNote());
            // task.setStatus(history);
            task.setStatus(
                TaskHistory.builder()
                    .id(row.getTaskHistoryId())
                    .status(TaskStatus.valueOf(row.getTaskHistoryStatus()))
                    .note(row.getTaskHistoryNote())
                    .build());
        }
        return task;
    }
}
