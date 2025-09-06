package com.giatrong.learning.learnspringapi.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.giatrong.learning.learnspringapi.dto.response.ApiResponse;
import com.giatrong.learning.learnspringapi.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    // ========== BUSINESS LOGIC EXCEPTIONS ==========
    
    /**
     * Handle custom business logic exceptions
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<?>> handleAppException(AppException ex, HttpServletRequest request) {
        ErrorCode errorCode = ex.getErrorCode();
        
        log.warn("AppException occurred: {} at {}", errorCode.getMessage(), request.getRequestURI(), ex);
        
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.error(errorCode.getStatusCode().value(), errorCode.getMessage()));
    }

      /**
     * Handle resource not found exceptions
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        log.warn("Resource not found for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        ErrorCode.RESOURCE_NOT_FOUND.getCode(),
                        ex.getMessage()
                ));
    }

    // ========== VALIDATION EXCEPTIONS ==========
    
    /**
     * Handle @Valid annotation validation errors (Request Body)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
        
        log.warn("Validation failed for request to {}: {}", request.getRequestURI(), errors);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .statusCode(ErrorCode.VALIDATION_FAILED.getCode())
                        .message("Validation failed")
                        .data(Map.of(
                                "errors", errors,
                                "timestamp", LocalDateTime.now(),
                                "path", request.getRequestURI()
                        ))
                        .build());
    }

    /**
     * Handle @Validated annotation validation errors (Path/Query parameters)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }
        
        log.warn("Constraint violation for request to {}: {}", request.getRequestURI(), errors);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .statusCode(ErrorCode.VALIDATION_FAILED.getCode())
                        .message("Validation failed")
                        .data(Map.of(
                                "errors", errors,
                                "timestamp", LocalDateTime.now(),
                                "path", request.getRequestURI()
                        ))
                        .build());
    }

    // ========== SECURITY EXCEPTIONS ==========
    
    /**
     * Handle Spring Security access denied exceptions
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("Access denied for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(
                        ErrorCode.ACCESS_DENIED.getCode(),
                        ErrorCode.ACCESS_DENIED.getMessage()
                ));
    }

    /**
     * Handle authentication failures
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        log.warn("Bad credentials for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(
                        ErrorCode.INVALID_CREDENTIALS.getCode(),
                        ErrorCode.INVALID_CREDENTIALS.getMessage()
                ));
    }

    /**
     * Handle disabled user account
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<?>> handleDisabled(DisabledException ex, HttpServletRequest request) {
        log.warn("Disabled account access attempt for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(
                        ErrorCode.USER_DISABLED.getCode(),
                        ErrorCode.USER_DISABLED.getMessage()
                ));
    }

    /**
     * Handle locked user account
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ApiResponse<?>> handleLocked(LockedException ex, HttpServletRequest request) {
        log.warn("Locked account access attempt for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(
                        ErrorCode.USER_LOCKED.getCode(),
                        ErrorCode.USER_LOCKED.getMessage()
                ));
    }

    // ========== HTTP EXCEPTIONS ==========
    
    /**
     * Handle HTTP method not supported
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.warn("Method not supported for request to {}: {} method not allowed", request.getRequestURI(), ex.getMethod());
        
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.error(
                        ErrorCode.METHOD_NOT_ALLOWED.getCode(),
                        String.format("HTTP method %s not supported for this endpoint", ex.getMethod())
                ));
    }

    /**
     * Handle unsupported media type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        log.warn("Media type not supported for request to {}: {}", request.getRequestURI(), ex.getContentType());
        
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(ApiResponse.error(
                        ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode(),
                        ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage()
                ));
    }

    /**
     * Handle missing request parameters
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<?>> handleMissingParameter(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("Missing parameter for request to {}: {} parameter '{}' is required", 
                request.getRequestURI(), ex.getParameterType(), ex.getParameterName());
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        ErrorCode.MISSING_PARAMETER.getCode(),
                        String.format("Required parameter '%s' is missing", ex.getParameterName())
                ));
    }

    /**
     * Handle method argument type mismatch
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn("Type mismatch for request to {}: parameter '{}' should be of type {}", 
                request.getRequestURI(), ex.getName(), ex.getRequiredType().getSimpleName());
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        ErrorCode.INVALID_PARAMETER.getCode(),
                        String.format("Parameter '%s' should be of type %s", ex.getName(), ex.getRequiredType().getSimpleName())
                ));
    }

    /**
     * Handle malformed JSON
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("Malformed JSON for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        String message = "Invalid JSON format";
        if (ex.getCause() instanceof JsonParseException) {
            message = "Malformed JSON: " + ex.getCause().getMessage();
        }
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        ErrorCode.INVALID_JSON.getCode(),
                        message
                ));
    }

    /**
     * Handle resource not found (404)
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        log.warn("No handler found for request to {}: {} {}", request.getRequestURI(), ex.getHttpMethod(), ex.getRequestURL());
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        ErrorCode.RESOURCE_NOT_FOUND.getCode(),
                        String.format("No handler found for %s %s", ex.getHttpMethod(), ex.getRequestURL())
                ));
    }

    // ========== DATABASE EXCEPTIONS ==========
    
    /**
     * Handle database constraint violations (e.g., unique constraint)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.error("Data integrity violation for request to {}: {}", request.getRequestURI(), ex.getMessage());
        
        String message = "Data integrity violation";
        if (ex.getMessage().contains("Duplicate entry")) {
            message = "Resource already exists";
        }
        
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(
                        ErrorCode.RESOURCE_ALREADY_EXISTS.getCode(),
                        message
                ));
    }

    // ========== FALLBACK EXCEPTIONS ==========
    
    /**
     * Handle all other runtime exceptions
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        log.error("Runtime exception for request to {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                        "An unexpected error occurred"
                ));
    }

    /**
     * Handle all other exceptions (fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception for request to {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                        "An unexpected error occurred"
                ));
    }
}
