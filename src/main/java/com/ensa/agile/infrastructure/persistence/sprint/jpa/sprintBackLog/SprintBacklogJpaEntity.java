package com.ensa.agile.infrastructure.persistence.sprint.jpa;

import java.time.LocalDate;

import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaEntity;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "sprint_backlogs")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SprintBacklogJpaEntity extends BaseJpaEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProductBackLogJpaEntity project;

    @ManyToOne
    @JoinColumn(name = "scrum_master_id", nullable = false)
    private UserJpaEntity scrumMaster;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String goal;
}
