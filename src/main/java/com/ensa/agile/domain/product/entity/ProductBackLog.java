package com.ensa.agile.domain.product.entity;

import java.time.LocalDateTime;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductBackLog extends BaseDomainEntity {

	private  String name;

	private  String description;

	public ProductBackLog(String name, String description) {
		super(null);
		this.name = name;
		this.description = description;
	}

	public ProductBackLog(String id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
	}

	public ProductBackLog(String id, String name, String description,
			LocalDateTime createdDate, String createdBy) {
		super(id, createdDate, createdBy, null, null);
		this.name = name;
		this.description = description;
	}

	public ProductBackLog(String id, String name, String description,
			LocalDateTime createdDate, String createdBy, LocalDateTime lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.name = name;
		this.description = description;
	}

}
