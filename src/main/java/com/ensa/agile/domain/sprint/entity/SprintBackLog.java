package com.ensa.agile.domain.sprint.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SprintBackLog extends BaseDomainEntity {

	private final String name;

	private final ProductBackLog productBackLog;

	private final User scrumMaster;

	private final LocalDate startDate;

	private final LocalDate endDate;

	private final String goal;

	public SprintBackLog(String name, ProductBackLog productBackLog, User scrumMaster, LocalDate startDate,
			LocalDate endDate,
			String goal) {
		super(null);
		this.name = name;
		this.productBackLog = productBackLog;
		this.scrumMaster = scrumMaster;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goal = goal;
	}

	public SprintBackLog(String id, String name, ProductBackLog productBackLog, User scrumMaster, LocalDate startDate,
			LocalDate endDate, String goal,
			LocalDateTime createdDate, String createdBy, LocalDateTime lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.name = name;
		this.productBackLog = productBackLog;
		this.scrumMaster = scrumMaster;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goal = goal;
	}
}
