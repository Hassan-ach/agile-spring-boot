package com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintMembers;

import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog.SprintBackLogJpaEntity;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "sprint_members")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SprintMemberJpaEntity extends BaseJpaEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = false)
    private SprintBackLogJpaEntity sprintBackLog;
}
