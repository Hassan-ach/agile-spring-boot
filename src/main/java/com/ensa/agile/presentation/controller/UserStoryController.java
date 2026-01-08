package com.ensa.agile.presentation.controller;

import com.ensa.agile.application.story.request.UserStoryCreateRequest;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.application.story.usecase.CreateUserStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/{productId}/user-story")
public class UserStoryController {

    private final CreateUserStoryUseCase createUserStoryUseCase;

    @PostMapping
    public ResponseEntity<UserStoryResponse>
    createUserStory(@RequestBody UserStoryCreateRequest request,
                    @PathVariable String productId) {

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createUserStoryUseCase.execute(
                new UserStoryCreateRequest(productId, request)));
    }
}
