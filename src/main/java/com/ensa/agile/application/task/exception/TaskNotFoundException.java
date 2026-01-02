package com.ensa.agile.application.task.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(String id) { super("Task", id); }
    public TaskNotFoundException() { super("Task"); }
}
