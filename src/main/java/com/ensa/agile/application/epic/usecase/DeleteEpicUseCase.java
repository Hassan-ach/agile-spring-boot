package com.ensa.agile.application.epic.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.common.response.DeleteResponse;
import com.ensa.agile.application.epic.exception.EpicNotFoundException;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.domain.epic.repository.EpicRepository;

@Component
public class DeleteEpicUseCase extends BaseUseCase<String, DeleteResponse> {
    private final EpicRepository epicRepository;

    public DeleteEpicUseCase(ITransactionalWrapper tr, EpicRepository ep) {
        super(tr);
        this.epicRepository = ep;
    }

    @Override
    public DeleteResponse execute(String epicId) {
        if (!isEpicExist(epicId)) {
            throw new EpicNotFoundException();
        }

        epicRepository.deleteById(epicId);

        return DeleteResponse.builder()
            .message("Epic deleted successfully")
            .success(true)
            .build();
    }

    private boolean isEpicExist(String epicId) {
        return epicRepository.existsById(epicId);
    }
}
