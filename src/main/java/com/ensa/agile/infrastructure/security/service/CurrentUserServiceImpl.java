package com.ensa.agile.infrastructure.security.service;

import com.ensa.agile.application.global.ICurrentUser;
import com.ensa.agile.domain.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements ICurrentUser {
  public User getCurrentUser() {
    return (User)SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
  }
}
