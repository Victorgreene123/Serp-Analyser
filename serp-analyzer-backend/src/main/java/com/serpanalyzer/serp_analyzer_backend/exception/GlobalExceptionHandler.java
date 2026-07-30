package com.serpanalyzer.serp_analyzer_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationFailure(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Invalid request", LocalDateTime.now()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Invalid request", LocalDateTime.now()));
    }

    @ExceptionHandler(SearchException.class)
    public ResponseEntity<ErrorResponse> handleSearchException(SearchException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(CrawlException.class)
    public ResponseEntity<ErrorResponse> handleCrawlException(CrawlException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal server error", LocalDateTime.now()));
    }
}
