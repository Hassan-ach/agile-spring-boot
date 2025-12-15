package com.ensa.agile.presentation.controller;

import com.ensa.agile.application.epic.request.EpicCreateRequest;
import com.ensa.agile.application.epic.request.EpicUpdateRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.epic.usecase.CreateEpicUseCase;
import com.ensa.agile.application.epic.usecase.UpdateEpicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<EpicResponse>
    createEpic(@PathVariable String productId,
               @RequestBody EpicCreateRequest request) {

        request.setProductId(productId);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createEpicUseCase.executeTransactionally(request));
    }

    @PatchMapping("/{epicId}")
    public ResponseEntity<EpicResponse>
    updateEpic(@PathVariable String epicId,
               @RequestBody EpicUpdateRequest request) {

        request.setId(epicId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(updateEpicUseCase.executeTransactionally(request));
    }
}
