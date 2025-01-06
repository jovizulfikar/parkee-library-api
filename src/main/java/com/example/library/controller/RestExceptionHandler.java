package com.example.library.controller;

import com.example.library.exception.AppException;
import com.example.library.helper.ExceptionTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        ProblemDetail detail = ExceptionTranslator.defaultProblemDetail();
        return ResponseEntity.of(detail).build();
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        ProblemDetail detail = ExceptionTranslator.toProblemDetail(e);
        return ResponseEntity.of(detail).build();
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleException(AppException e) {
        log.error("AppException: {}", e.getMessage(), e);
        ProblemDetail detail = ExceptionTranslator.toProblemDetail(e);
        return ResponseEntity.of(detail).build();
    }

}
