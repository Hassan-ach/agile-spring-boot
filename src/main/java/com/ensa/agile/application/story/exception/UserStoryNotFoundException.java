package com.ensa.agile.application.story.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class UserStoryNotFoundException extends NotFoundException {
    public UserStoryNotFoundException(String id) { super("User Story", id); }
    public UserStoryNotFoundException() { super("User Story"); }
}
