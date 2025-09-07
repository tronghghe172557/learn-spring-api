package com.giatrong.learning.learnspringapi.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/**
 * how to declare an enum:
 * public enum EnumName {
 * CONSTANT_NAME1(value1, value2),
 * CONSTANT_NAME2(value1, value2),
 * ...
 * }
 */
@Getter
public enum ErrorCode {

    // ========== SYSTEM ERRORS  ==========
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Uncategorized exception",
            HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "Service temporarily unavailable",
            HttpStatus.SERVICE_UNAVAILABLE),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Database connection error",
            HttpStatus.INTERNAL_SERVER_ERROR),

    // ========== VALIDATION ERRORS ==========
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST.value(), "Validation failed", HttpStatus.BAD_REQUEST),
    INVALID_INPUT(HttpStatus.BAD_REQUEST.value(), "Invalid input data", HttpStatus.BAD_REQUEST),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST.value(), "Required field is missing", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST.value(), "Invalid data format", HttpStatus.BAD_REQUEST),
    INVALID_JSON(1004, "Invalid JSON format", HttpStatus.BAD_REQUEST),

    // ========== AUTHENTICATION ERRORS ==========
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(), "Authentication required", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "Token has expired", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Invalid or malformed token", HttpStatus.UNAUTHORIZED),
    TOKEN_MISSING(HttpStatus.UNAUTHORIZED.value(), "Authorization token is missing", HttpStatus.UNAUTHORIZED),

    // ========== AUTHORIZATION ERRORS ==========
    ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "Access denied", HttpStatus.FORBIDDEN),
    INSUFFICIENT_PRIVILEGES(HttpStatus.FORBIDDEN.value(), "Insufficient privileges", HttpStatus.FORBIDDEN),
    RESOURCE_FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Access to this resource is forbidden", HttpStatus.FORBIDDEN),

    // ========== USER ERRORS ==========
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "User already exists", HttpStatus.CONFLICT),
    USERNAME_ALREADY_TAKEN(HttpStatus.CONFLICT.value(), "Username is already taken", HttpStatus.CONFLICT),
    EMAIL_ALREADY_TAKEN(HttpStatus.CONFLICT.value(), "Email is already taken", HttpStatus.CONFLICT),
    USER_DISABLED(HttpStatus.FORBIDDEN.value(), "User account is disabled", HttpStatus.FORBIDDEN),
    USER_LOCKED(HttpStatus.FORBIDDEN.value(), "User account is locked", HttpStatus.FORBIDDEN),

    // ========== VALIDATION SPECIFIC ERRORS ==========
    USERNAME_INVALID(HttpStatus.BAD_REQUEST.value(), "Username must be at least {min} characters",
            HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST.value(), "Password must be at least {min} characters",
            HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(HttpStatus.BAD_REQUEST.value(), "Invalid email format", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(HttpStatus.BAD_REQUEST.value(), "Invalid phone number format", HttpStatus.BAD_REQUEST),
    DOB_INVALID(HttpStatus.BAD_REQUEST.value(), "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_WEAK(HttpStatus.BAD_REQUEST.value(), "Password does not meet security requirements",
            HttpStatus.BAD_REQUEST),

    // ========== RESOURCE ERRORS ==========
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Resource not found", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "Resource already exists", HttpStatus.CONFLICT),
    RESOURCE_CONFLICT(HttpStatus.CONFLICT.value(), "Resource conflict", HttpStatus.CONFLICT),

    // ========== REQUEST ERRORS ==========
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad request", HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(), "Invalid parameter", HttpStatus.BAD_REQUEST),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST.value(), "Missing required parameter", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "HTTP method not allowed", HttpStatus.METHOD_NOT_ALLOWED),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "Unsupported media type",
            HttpStatus.UNSUPPORTED_MEDIA_TYPE),

    // ========== RATE LIMITING & QUOTA ERRORS ==========
    RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS.value(), "Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS),
    QUOTA_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS.value(), "Quota exceeded", HttpStatus.TOO_MANY_REQUESTS),

    // ========== LEGACY CODES (for backward compatibility) ==========
    INVALID_KEY(HttpStatus.BAD_REQUEST.value(), "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(HttpStatus.CONFLICT.value(), "User existed", HttpStatus.CONFLICT), // Maps to USER_ALREADY_EXISTS
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND.value(), "User is not existed", HttpStatus.NOT_FOUND), // Maps to
                                                                                                 // USER_NOT_FOUND
    UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "You do not have permission", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Role not found", HttpStatus.NOT_FOUND);
                                                                                                    // ACCESS_DENIED
    // == Constructor ==
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    // == Fields ==
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}