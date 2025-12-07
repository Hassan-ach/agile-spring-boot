package com.ensa.agile.domain.product.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductBackLog extends BaseDomainEntity {

	private final String name;

	private final String description;

	public ProductBackLog(String name, String description) {
		super(null);
		this.name = name;
		this.description = description;
	}

	public ProductBackLog(String id, String name, String description,
			LocalDate createdDate, String createdBy, LocalDate lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.name = name;
		this.description = description;
	}

}
