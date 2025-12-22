package com.ensa.agile.infrastructure.persistence.product.jpa.member;

import com.ensa.agile.domain.product.enums.MemberStatus;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.product.jpa.backlog.ProductBackLogJpaEntity;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project_members")
public class ProjectMemberJpaEntity extends BaseJpaEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;

  @ManyToOne(optional = false)
  @JoinColumn(name = "product_backlog_id", nullable = false)
  private ProductBackLogJpaEntity productBackLog;

  @Enumerated(EnumType.STRING) private RoleType role;

  @Enumerated(EnumType.STRING) private MemberStatus status;
}
