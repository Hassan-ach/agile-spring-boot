package com.ensa.agile.infrastructure.persistence.task.jpa.history;

import com.ensa.agile.domain.task.enums.TaskStatus;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.task.jpa.task.TaskJpaEntity;
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

@Table(name = "task_history")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskHistoryJpaEntity extends BaseJpaEntity {

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskJpaEntity task;

    @Enumerated(EnumType.STRING) private TaskStatus status;

    private String note;
}
