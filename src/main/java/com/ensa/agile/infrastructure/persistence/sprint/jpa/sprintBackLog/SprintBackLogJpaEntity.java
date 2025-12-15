package com.ensa.agile.infrastructure.persistence.sprint.jpa.sprintBackLog;

import com.ensa.agile.domain.sprint.enums.SprintStatus;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaEntity;
import com.ensa.agile.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
public class SprintBackLogJpaEntity extends BaseJpaEntity {

    @Column(nullable = false) private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProductBackLogJpaEntity productBackLog;

    @ManyToOne
    @JoinColumn(name = "scrum_master_id", nullable = false)
    private UserJpaEntity scrumMaster;

    @Enumerated(EnumType.STRING) private SprintStatus status;

    @Column(name = "start_date", nullable = false) private LocalDate startDate;

    @Column(name = "end_date", nullable = false) private LocalDate endDate;

    @Column(nullable = false) private String goal;
}
