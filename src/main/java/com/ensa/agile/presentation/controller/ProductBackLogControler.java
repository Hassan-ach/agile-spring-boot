package com.ensa.agile.presentation.controller;

import com.ensa.agile.application.common.request.InviteRequest;
import com.ensa.agile.application.common.response.InviteResponse;
import com.ensa.agile.application.product.request.ProductBackLogCreateRequest;
import com.ensa.agile.application.product.request.ProductBackLogUpdateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.product.usecase.CreateProductBackLogUseCase;
import com.ensa.agile.application.product.usecase.InviteScrumMasterUseCase;
import com.ensa.agile.application.product.usecase.UpdateProductBackLogInfoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-backlog")
public class ProductBackLogControler {

    private final CreateProductBackLogUseCase createProductBacklogUseCase;
    private final UpdateProductBackLogInfoUseCase updateProductBackLogUseCase;
    private final InviteScrumMasterUseCase inviteScrumMasterUseCase;

    @PostMapping("/create")
    public ResponseEntity<ProductBackLogResponse> createProductBacklog(
        @Valid @RequestBody ProductBackLogCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createProductBacklogUseCase.executeTransactionally(request));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductBackLogResponse> updateProductBacklog(
        @Valid @RequestBody ProductBackLogUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(updateProductBackLogUseCase.executeTransactionally(request));
    }

    @PostMapping("/invite/scrum-master")
    public ResponseEntity<InviteResponse>
    inviteScrumMaster(@Valid @RequestBody InviteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(inviteScrumMasterUseCase.executeTransactionally(request));
    }
}
