package com.ensa.agile.presentation.advice;

import com.ensa.agile.application.product.exception.EpicNotFoundException;
import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.application.product.exception.UserAlreadyInvitedException;
import com.ensa.agile.application.user.exception.AuthenticationFailureException;
import com.ensa.agile.application.user.exception.EmailAlreadyUsedExeption;
import com.ensa.agile.application.user.exception.InvalidCredentialsException;
import com.ensa.agile.application.user.exception.UserNotFoundException;
import com.ensa.agile.domain.global.exception.AlreadyExistException;
import com.ensa.agile.domain.global.exception.ApplicationException;
import com.ensa.agile.domain.global.exception.DataBaseTransactionException;
import com.ensa.agile.domain.global.exception.DomainException;
import com.ensa.agile.domain.global.exception.ForbidException;
import com.ensa.agile.domain.global.exception.ValidationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, String>>

    buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<Map<String, String>>
        handleUnauthorizedExceptions(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ValidationException.class,
                       InvalidCredentialsException.class})
    public ResponseEntity<Map<String, String>>
        handleBadRequestExceptions(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbidException.class)
    public ResponseEntity<Map<String, String>>
    handleForbidException(ForbidException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NotFoundException.class, DomainException.class,
                       UserNotFoundException.class, EpicNotFoundException.class,
                       ProductBackLogNotFoundException.class})
    public ResponseEntity<Map<String, String>>
        handleNotFoundExceptions(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AlreadyExistException.class,
                       EmailAlreadyUsedExeption.class})
    public ResponseEntity<Map<String, String>>
        handleConflictExceptions(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>>
    handleIllegalStateException(IllegalStateException ex) {
        return buildErrorResponse(ex.getMessage(),
                                  HttpStatus.UNPROCESSABLE_CONTENT);
    }

    @ExceptionHandler({AuthenticationFailureException.class})
    public ResponseEntity<Map<String, String>>
        HandleJwtExceptions(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Map<String, String>>
        handleApplicationExceptions(ApplicationException ex) {
        return buildErrorResponse("An unexpected internal error occurred.",
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({DataBaseTransactionException.class})
    public ResponseEntity<Map<String, String>>
        handleDataBaseException(DataBaseTransactionException ex) {
        return buildErrorResponse("A database error occurred.",
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserAlreadyInvitedException.class})
    public ResponseEntity<Map<String, String>>
        handleUserAlreadyInvitedException(UserAlreadyInvitedException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>>
    handleUncaughtRuntimeException(RuntimeException ex) {

        return buildErrorResponse("A critical system error occurred.",
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
