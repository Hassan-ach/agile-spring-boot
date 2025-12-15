package com.ensa.agile.application.global.service;

public interface IAbacService {
    boolean canAccessSprint(String sprintId, String action);
    boolean canAccessProject(String projectId, String action);
}
