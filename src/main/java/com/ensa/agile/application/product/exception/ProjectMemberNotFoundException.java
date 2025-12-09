package com.ensa.agile.application.product.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class ProjectMemberNotFoundException extends NotFoundException {

    public ProjectMemberNotFoundException(String projectMemberId) {
        super("Project", projectMemberId);
    }

    public ProjectMemberNotFoundException() { super("Project"); }
}
