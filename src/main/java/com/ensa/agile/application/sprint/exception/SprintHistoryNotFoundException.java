package com.ensa.agile.application.sprint.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class SprintHistoryNotFoundException extends NotFoundException {
    public SprintHistoryNotFoundException(String id) {
        super("Sprint History", id);
    }

    public SprintHistoryNotFoundException() { super("Sprint History"); }
}
