package com.iapurba.bookapp.exception;

import com.iapurba.bookapp.util.ErrorConstant;
import com.iapurba.bookapp.util.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Generic Error Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ErrorConstant.SERVER_ERROR_CODE, ex.getMessage()
        );
        ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorConstant.SERVER_ERROR_MESSAGE,
                errorDetails
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseWrapper);
    }

    // Validation Error Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<?>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();

        // Extract field specific validation errors
        ex.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));

        ErrorDetails errorDetails = new ErrorDetails(ErrorConstant.VALIDATION_ERROR_CODE, validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseWrapper<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", errorDetails));
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ResponseWrapper<?>> handleDuplicateBookException(DuplicateBookException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                ErrorConstant.DUPLICATE_ERROR_CODE, ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseWrapper<>(HttpStatus.CONFLICT.value(), "Duplicate Error", errorDetails));
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ResponseWrapper<?>> authorNotFoundException(AuthorNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                ErrorConstant.AUTHOR_NOT_FOUND_CODE, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseWrapper<>(HttpStatus.NOT_FOUND.value(), "Not Found", errorDetails));
    }
}
