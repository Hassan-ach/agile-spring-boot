package com.ensa.agile.domain.epic.repository;

import java.util.List;

import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.row.EpicRow;
import com.ensa.agile.domain.global.repository.BaseDomainRepository;

public interface EpicRepository extends BaseDomainRepository<Epic, String> {
    List<Epic> findAllByProductBackLogId(String productBackLogId);

    List<EpicRow> loadEpicRowsById(String id);
}
