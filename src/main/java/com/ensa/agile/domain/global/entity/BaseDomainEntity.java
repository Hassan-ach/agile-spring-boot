package com.ensa.agile.domain.global.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseDomainEntity {
    private final String id;
    private final LocalDate createdADate;
    private final String createdBy;
    private final LocalDate lastModifiedDate;
    private final String lastModifiedBy;

    public BaseDomainEntity(String id) {
        this.id = id;
        this.createdADate = LocalDate.now();
        this.createdBy = null;
        this.lastModifiedDate = null;
        this.lastModifiedBy = null;
    }
}
