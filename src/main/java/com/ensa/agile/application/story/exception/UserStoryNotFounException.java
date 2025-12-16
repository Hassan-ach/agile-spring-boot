package com.ensa.agile.application.story.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class UserStoryNotFounException extends NotFoundException {
    public UserStoryNotFounException(String id) { super("User Story", id); }
    public UserStoryNotFounException() { super("User Story"); }
}
