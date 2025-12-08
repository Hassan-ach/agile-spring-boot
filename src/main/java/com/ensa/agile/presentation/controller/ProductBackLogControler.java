package com.ensa.agile.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensa.agile.application.product.request.ProductBackLogCreateRequest;
import com.ensa.agile.application.product.request.ProductBackLogUpdateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.product.usecase.CreateProductBackLogUseCase;
import com.ensa.agile.application.product.usecase.UpdateProductBackLogInfoUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-backlog")
public class ProductBackLogControler {

    private final CreateProductBackLogUseCase createProductBacklogUseCase;
    private final UpdateProductBackLogInfoUseCase updateProductBackLogUseCase;

    @PostMapping("/create")
    public ResponseEntity<ProductBackLogResponse> createProductBacklog(
            @Valid @RequestBody ProductBackLogCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductBacklogUseCase.execute(request));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductBackLogResponse> updateProductBacklog(
            @Valid @RequestBody ProductBackLogUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(updateProductBackLogUseCase.execute(request));
    }
}
