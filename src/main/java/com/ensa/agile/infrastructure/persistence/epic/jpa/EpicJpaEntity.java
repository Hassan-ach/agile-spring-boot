package com.ensa.agile.infrastructure.persistence.epic.jpa;

import java.time.LocalDateTime;

import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog.ProductBackLogJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "epics")
public class EpicJpaEntity extends BaseJpaEntity {

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "product_backlog_id", nullable = false)
    private ProductBackLogJpaEntity productBackLog;

    public EpicJpaEntity(String id, String title, String description,
                         ProductBackLogJpaEntity productBackLog,
                         String createdBy, LocalDateTime createdDate,
                         String lastModifiedBy,
                         LocalDateTime lastModifiedDate) {

        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.title = title;
        this.description = description;
        this.productBackLog = productBackLog;
    }
}
