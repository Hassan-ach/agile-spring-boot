package com.ensa.agile.domain.global.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseDomainEntity {
  private final String id;
  private final LocalDateTime createdDate;
  private final String createdBy;
  private final LocalDateTime lastModifiedDate;
  private final String lastModifiedBy;

  public BaseDomainEntity(String id) {
    this.id = id;
    this.createdDate = LocalDateTime.now();
    this.createdBy = null;
    this.lastModifiedDate = null;
    this.lastModifiedBy = null;
  }
}
