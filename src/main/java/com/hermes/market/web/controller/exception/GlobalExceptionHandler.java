package com.hermes.market.web.controller.exception;

import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {

        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException exception, HttpServletRequest request) {

        String error = "Business rule violation";
        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {

        String error = "Invalid argument";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {

        String error = "Data integrity violation";
        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationException( MethodArgumentNotValidException exception, HttpServletRequest request){

        String error = "Validation error";
        String message = "Invalid fields";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        log.warn("Validation error on request {}", request.getRequestURI());

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> genericException(Exception exception, HttpServletRequest request) {

        String error = "Internal server error";
        String message = "An unexpected error occurred";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        log.error("Unexpected error", exception);

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardError> noResourceFoundException(NoResourceFoundException exception, HttpServletRequest request) {

        String error = "No resource found";
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }
}