package com.ensa.agile.application.sprint.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class SprintMemberNotFoundException extends NotFoundException {
    public SprintMemberNotFoundException(String userEmail) {
        super("Sprint member", userEmail);
    }
    public SprintMemberNotFoundException() { super("Sprint member"); }
}
