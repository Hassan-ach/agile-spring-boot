package com.ensa.agile.application.task.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class TaskHistoryNotFoundException extends NotFoundException {

    public TaskHistoryNotFoundException(String id) { super("TaskHistory", id); }
    public TaskHistoryNotFoundException() { super("TaskHistory"); }
}
