package com.ensa.agile.application.sprint.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class SprintBackLogNotFoundException extends NotFoundException {
    public SprintBackLogNotFoundException() { super("Sprint BackLog"); }
    public SprintBackLogNotFoundException(String id) {
        super("Sprint BackLog", id);
    }
}
