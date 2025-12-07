package com.ensa.agile.domain.product.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Epic extends BaseDomainEntity {

	private final String title;

	private final String description;

	private final ProductBackLog productBackLog;

	public Epic(String title, String description, ProductBackLog productBackLog) {
		super(null);
		this.title = title;
		this.description = description;
		this.productBackLog = productBackLog;
	}

	public Epic(String id, String title, String description, ProductBackLog productBackLog, LocalDate createdDate,
			String createdBy,
			LocalDate lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.title = title;
		this.description = description;
		this.productBackLog = productBackLog;
	}
}
