package com.ensa.agile.domain.product.exception;

import com.ensa.agile.domain.global.exception.DomainException;

public class ProjectMemberNotFoundException extends DomainException {

  public ProjectMemberNotFoundException(String projectMember) {
    super("Project Member with ID " + projectMember + " not found.");
  }

  public ProjectMemberNotFoundException() {
    super("Project Member not found.");
  }
}
