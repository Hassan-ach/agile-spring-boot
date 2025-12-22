package com.ensa.agile.domain.product.entity;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.product.enums.MemberStatus;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProjectMember extends BaseDomainEntity {

    private final User user;
    private final ProductBackLog productBackLog;
    private RoleType role;
    private MemberStatus status;

    // public ProjectMember(User user, ProductBackLog productBackLog,
    //                      RoleType role, MemberStatus status) {
    //     super(null);
    //     this.user = user;
    //     this.productBackLog = productBackLog;
    //     this.role = role;
    //     this.status = status;
    // }

    public ProjectMember(String id, User user, ProductBackLog productBackLog,
                         RoleType role, MemberStatus status,
                         LocalDateTime createdDate, String createdBy,
                         LocalDateTime lastModifiedDate,
                         String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.user = user;
        this.productBackLog = productBackLog;
        this.role = role;
        this.status = status;
    }

    public void activate() {
        if (this.user == null) {
            throw new IllegalStateException(
                "Cannot activate a project member without a user.");
        }
        if (this.status != MemberStatus.INVITED ||
            this.status == MemberStatus.ACTIVE) {
            throw new IllegalStateException(
                "Can only activate a project member who is in INVITED status.");
        }
        this.status = MemberStatus.ACTIVE;
        return;
    }

    public void deactivate() {
        if (this.user == null) {
            throw new IllegalStateException(
                "Cannot deactivate a project member without a user.");
        }
        if (this.status != MemberStatus.ACTIVE) {
            throw new IllegalStateException("Can only deactivate a project "
                                            +
                                            "member who is in ACTIVE status.");
        }
        this.status = MemberStatus.INACTIVE;
    }

    public void setProducOwnerRole() {
        if (this.role == RoleType.PRODUCT_OWNER) {
            throw new IllegalStateException(
                "Project member is already a Product Owner.");
        }
        this.role = RoleType.PRODUCT_OWNER;
    }

    public void setScrumMasterRole() {
        if (this.role == RoleType.SCRUM_MASTER) {
            throw new IllegalStateException(
                "Project member is already a Scrum Master.");
        }
        this.role = RoleType.SCRUM_MASTER;
    }

    public void setDeveloperRole() {
        if (this.role == RoleType.DEVELOPER) {
            throw new IllegalStateException(
                "Project member is already a Developer.");
        }
        this.role = RoleType.DEVELOPER;
    }
}
