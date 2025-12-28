package com.ensa.agile.infrastructure.persistence.service;

import java.util.List;

public final class FetchPlan {

    boolean members;
    boolean sprints;
    boolean sprintMembers;
    boolean sprintStories;
    boolean sprintStoryTasks;
    boolean epics;
    boolean epicStories;
    boolean epicStoryTasks;
    boolean orphanStories;
    boolean orphanStoryTasks;

    public static FetchPlan resolve(List<String> fetchFields) {
        FetchPlan p = new FetchPlan();

        if (FetchField.isAll(fetchFields)) {
            p.members = p.sprints = p.sprintMembers = true;
            p.sprintStories = p.sprintStoryTasks = true;
            p.epics = p.epicStories = true;
            p.orphanStories = p.orphanStoryTasks = true;
            return p;
        }

        p.members = FetchField.has(fetchFields, FetchField.MEMBERS);

        p.sprints = FetchField.has(fetchFields, FetchField.SPRINTS);
        p.sprintMembers =
            FetchField.has(fetchFields, FetchField.SPRINTS_MEMBERS);
        p.sprintStories =
            FetchField.has(fetchFields, FetchField.SPRINTS_USER_STORIES);
        p.sprintStoryTasks =
            FetchField.has(fetchFields, FetchField.SPRINTS_USER_STORIES_TASKS);

        p.epics = FetchField.has(fetchFields, FetchField.EPICS);
        p.epicStories =
            FetchField.has(fetchFields, FetchField.EPICS_USER_STORIES);
        p.epicStoryTasks =
            FetchField.has(fetchFields, FetchField.EPICS_USER_STORIES_TASKS);

        p.orphanStories = FetchField.has(fetchFields, FetchField.USER_STORIES);
        p.orphanStoryTasks =
            FetchField.has(fetchFields, FetchField.USER_STORIES_TASKS);

        return p;
    }
}
