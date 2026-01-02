package com.ensa.agile.application.story.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class UserStoryHistoryNotFoundException extends NotFoundException {
    public UserStoryHistoryNotFoundException(String id) {
        super("User Story History", id);
    }
    public UserStoryHistoryNotFoundException() { super("User Story History"); }
}
