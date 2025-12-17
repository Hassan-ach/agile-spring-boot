package com.ensa.agile.presentation.controller;

import com.ensa.agile.application.common.response.DeleteResponse;
import com.ensa.agile.application.epic.request.EpicCreateRequest;
import com.ensa.agile.application.epic.request.EpicGetRequest;
import com.ensa.agile.application.epic.request.EpicUpdateRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.epic.usecase.CreateEpicUseCase;
import com.ensa.agile.application.epic.usecase.DeleteEpicUseCase;
import com.ensa.agile.application.epic.usecase.GetEpicUseCase;
import com.ensa.agile.application.epic.usecase.UpdateEpicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{productId}/epics")
public class EpicControler {

    private final CreateEpicUseCase createEpicUseCase;
    private final UpdateEpicUseCase updateEpicUseCase;
    private final DeleteEpicUseCase deleteEpicUseCase;
    private final GetEpicUseCase getEpicUseCase;

    @PostMapping("/")
    public ResponseEntity<EpicResponse>
    createEpic(@PathVariable String productId,
               @RequestBody EpicCreateRequest request) {

        request.setProductId(productId);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createEpicUseCase.executeTransactionally(request));
    }

    @GetMapping("/{epicId}")
    public ResponseEntity<EpicResponse>
    getEpicById(@PathVariable String epicId, @PathVariable String productId) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                getEpicUseCase.executeTransactionally(EpicGetRequest.builder()
                                                          .epicId(epicId)
                                                          .productId(productId)
                                                          .build()));
    }

    @PatchMapping("/{epicId}")
    public ResponseEntity<EpicResponse>
    updateEpic(@PathVariable String epicId,
               @RequestBody EpicUpdateRequest request) {

        request.setId(epicId);
        request.setProductId(request.getProductId());

        return ResponseEntity.status(HttpStatus.OK)
            .body(updateEpicUseCase.executeTransactionally(request));
    }

    @DeleteMapping("/{epicId}")
    public ResponseEntity<DeleteResponse>
    deleteEpic(@PathVariable String epicId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(deleteEpicUseCase.executeTransactionally(epicId));
    }
}
