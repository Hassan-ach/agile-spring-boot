package com.ensa.agile.infrastructure.persistence.service;
import java.util.List;

public enum FetchField {

    ALL,

    MEMBERS,

    SPRINTS,
    SPRINTS_MEMBERS,
    SPRINTS_USER_STORIES,
    SPRINTS_USER_STORIES_TASKS,

    EPICS,
    EPICS_USER_STORIES,

    USER_STORIES,
    USER_STORIES_TASKS;

    public static boolean has(List<String> fields, FetchField f) {
        return fields != null && fields.contains(f.name());
    }

    public static boolean isAll(List<String> fields) {
        return fields != null && fields.contains(ALL.name());
    }
}
