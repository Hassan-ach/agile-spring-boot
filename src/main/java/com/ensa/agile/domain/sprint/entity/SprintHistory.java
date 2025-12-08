package com.ensa.agile.domain.sprint.entity;

import java.time.LocalDateTime;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.sprint.enums.SprintStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SprintHistory extends BaseDomainEntity {

	private final SprintBackLog sprint;

	private final SprintStatus status;

	private final String note;

	public SprintHistory(SprintBackLog sprint, SprintStatus status, String note) {
		super(null);
		this.sprint = sprint;
		this.status = status;
		this.note = note;
	}

	public SprintHistory(String id, SprintBackLog sprint, SprintStatus status, String note,
			LocalDateTime createdDate, String createdBy, LocalDateTime lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.sprint = sprint;
		this.status = status;
		this.note = note;
	}
}
