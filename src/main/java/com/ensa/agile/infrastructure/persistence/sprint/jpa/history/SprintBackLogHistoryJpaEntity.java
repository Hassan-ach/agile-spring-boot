package com.ensa.agile.infrastructure.persistence.sprint.jpa.history;

import com.ensa.agile.domain.sprint.enums.SprintStatus;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.backlog.SprintBackLogJpaEntity;
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

@Table(name = "sprint_backlogs_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SprintBackLogHistoryJpaEntity extends BaseJpaEntity {

    @ManyToOne
    @JoinColumn(name = "sprint_backlog_id", nullable = false)
    private SprintBackLogJpaEntity sprint;

    @Enumerated(EnumType.STRING) private SprintStatus status;

    private String note;
}
