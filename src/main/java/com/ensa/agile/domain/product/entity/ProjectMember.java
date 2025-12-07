package com.ensa.agile.domain.product.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.enums.MemberStatus;
import com.ensa.agile.domain.product.enums.RoleType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjectMember extends BaseDomainEntity {

	private final User user;

	private final ProductBackLog productBackLog;

	private final RoleType role;

	private final MemberStatus status;

	public ProjectMember(User user, ProductBackLog productBackLog, RoleType role, MemberStatus status) {
		super(null);
		this.user = user;
		this.productBackLog = productBackLog;
		this.role = role;
		this.status = status;
	}

	public ProjectMember(String id, User user, ProductBackLog productBackLog, RoleType role, MemberStatus status,
			LocalDate createdDate, String createdBy, LocalDate lastModifiedDate, String lastModifiedBy) {
		super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
		this.user = user;
		this.productBackLog = productBackLog;
		this.role = role;
		this.status = status;
	}

}
