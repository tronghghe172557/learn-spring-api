package com.giatrong.learning.learnspringapi.enums;

/**
 * Constants chứa tất cả các giá trị default và example được sử dụng trong Swagger documentation
 * Tập trung hóa các giá trị này để dễ dàng maintain và tái sử dụng
 */
public final class SwaggerDefaultValue {
    
    // ========== USER DATA EXAMPLES ==========
    public static final String USERNAME_EXAMPLE = "trong2003";
    public static final String PASSWORD_EXAMPLE = "Khongcopass@2002";
    public static final String FULL_NAME_EXAMPLE = "Hoàng Gia Trọng";
    public static final String FULL_NAME_UPDATED_EXAMPLE = "Hoàng Gia Trọng Update";
    public static final String EMAIL_EXAMPLE = "hoanggiatrang01@gmail.com";
    public static final String EMAIL_UPDATED_EXAMPLE = "hoanggiatrang02@gmail.com";
    public static final String ROLE_EXAMPLE = "USER";
    
    // ========== HEALTH CHECK EXAMPLES ==========
    public static final String HEALTH_STATUS_UP = "UP";
    public static final String HEALTH_STATUS_DOWN = "DOWN";
    public static final String APPLICATION_VERSION = "1.0.0";
    public static final String HEALTH_TIMESTAMP = "2024-01-15T10:30:00";
    public static final String APPLICATION_UPTIME = "3600000";
    public static final String ENVIRONMENT_PROFILE = "dev";
    
    // ========== SYSTEM INFO EXAMPLES ==========
    public static final String JAVA_VERSION = "21.0.1";
    public static final String OS_NAME = "Mac OS X";
    public static final String TOTAL_MEMORY = "512";
    public static final String FREE_MEMORY = "256";
    public static final String USED_MEMORY = "256";
    public static final String MEMORY_USAGE_PERCENTAGE = "50.0";
    
    // ========== API DESCRIPTIONS ==========
    public static final String USER_REGISTRATION_DESC = "User registration request";
    public static final String USER_LOGIN_DESC = "User login request";
    public static final String USER_CREATE_DESC = "User creation request";
    public static final String USER_UPDATE_DESC = "User update request";
    public static final String HEALTH_RESPONSE_DESC = "Health check response containing system status and metrics";
    public static final String API_RESPONSE_DESC = "Generic API response object";
    public static final String COMPONENT_HEALTH_DESC = "Individual component health status";
    public static final String ADDITIONAL_SYSTEM_INFO_DESC = "Additional system information";
    
    // ========== TAG NAMES ==========
    public static final String TAG_HEALTH_CHECK = "Health Check";
    public static final String TAG_AUTHENTICATION = "Authentication";
    public static final String TAG_USER_MANAGEMENT = "User Management";
    
    // ========== TAG DESCRIPTIONS ==========
    public static final String TAG_HEALTH_CHECK_DESC = "API for system health monitoring and status checking";
    public static final String TAG_AUTHENTICATION_DESC = "API for user authentication with comprehensive validation";
    public static final String TAG_USER_MANAGEMENT_DESC = "API for managing users";
    
    // ========== OPERATION SUMMARIES ==========
    public static final String OP_REGISTER_USER = "Register a new user";
    public static final String OP_USER_LOGIN = "User login";
    public static final String OP_TEST_AUTH = "Test authentication system";
    public static final String OP_GET_ALL_USERS = "Get all users";
    public static final String OP_GET_USER_BY_ID = "Get user by ID";
    public static final String OP_CREATE_USER = "Create new user";
    public static final String OP_UPDATE_USER = "Update user";
    public static final String OP_DELETE_USER = "Delete user";
    public static final String OP_CHECK_HEALTH = "Check system health status";
    public static final String OP_SIMPLE_HEALTH = "Simple health check";
    
    // ========== SECURITY SCHEME ==========
    public static final String BEARER_AUTH_NAME = "bearerAuth";
    public static final String JWT_BEARER_FORMAT = "JWT";
    public static final String HTTP_SCHEME = "bearer";
    
    // ========== OPEN API CONFIG ==========
    public static final String API_TITLE = "Learn Spring API";
    public static final String API_DESCRIPTION = "Spring Boot API documentation with Swagger (OpenAPI 3)";
    public static final String API_VERSION = "1.0.0";
    public static final String LICENSE_NAME = "Apache 2.0";
    public static final String LICENSE_URL = "http://springdoc.org";
    public static final String EXTERNAL_DOCS_DESC = "Project Documentation";
    public static final String EXTERNAL_DOCS_URL = "https://github.com/giatrong/learn-spring-api";
    
    // ========== RESPONSE CODES ==========
    public static final String HTTP_200 = "200";
    public static final String HTTP_201 = "201";
    public static final String HTTP_503 = "503";
    
    // ========== RESPONSE DESCRIPTIONS ==========
    public static final String RESPONSE_200_DESC = "Health check completed successfully";
    public static final String RESPONSE_503_DESC = "Service unavailable - health check failed";
    
    // ========== FIELD DESCRIPTIONS ==========
    public static final String USERNAME_DESC = "Username for the new account";
    public static final String USERNAME_AUTH_DESC = "Username for authentication";
    public static final String USERNAME_UNIQUE_DESC = "Unique username for the user";
    public static final String PASSWORD_DESC = "Password for the new account (min 8 chars, must contain uppercase, lowercase, digit, and special character)";
    public static final String PASSWORD_AUTH_DESC = "Password for authentication";
    public static final String PASSWORD_ACCOUNT_DESC = "Password for the account";
    public static final String FULL_NAME_DESC = "Full name of the user";
    public static final String EMAIL_DESC = "Email address of the user";
    public static final String ROLE_DESC = "RoleEnum of the user";
    
    // ========== HEALTH FIELD DESCRIPTIONS ==========
    public static final String OVERALL_STATUS_DESC = "Overall system status";
    public static final String APP_VERSION_DESC = "Application version";
    public static final String TIMESTAMP_DESC = "Current server timestamp";
    public static final String UPTIME_DESC = "Application uptime in milliseconds";
    public static final String ENVIRONMENT_DESC = "Environment profile";
    public static final String COMPONENT_STATUS_DESC = "Detailed component health status";
    public static final String SYSTEM_INFO_DESC = "System information";
    public static final String COMP_STATUS_DESC = "Component status";
    public static final String COMP_DETAILS_DESC = "Component details";
    public static final String JAVA_VERSION_DESC = "Java version";
    public static final String OS_NAME_DESC = "Operating system";
    public static final String TOTAL_MEMORY_DESC = "Total memory in MB";
    public static final String FREE_MEMORY_DESC = "Free memory in MB";
    public static final String USED_MEMORY_DESC = "Used memory in MB";
    public static final String MEMORY_USAGE_DESC = "Memory usage percentage";
    
    // ========== SUCCESS MESSAGES ==========
    public static final String USER_REGISTERED_SUCCESS = "User registered successfully";
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String TEST_SUCCESS = "Test successful";
    public static final String GET_USERS_SUCCESS = "Get all users successfully";
    public static final String GET_USER_SUCCESS = "Get user by ID successfully";
    public static final String USER_CREATED_SUCCESS = "User created successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String HEALTH_CHECK_SUCCESS = "System is healthy and operational";
    public static final String SIMPLE_HEALTH_SUCCESS = "Simple health check successful";
    
    // ========== APPLICATION MESSAGES ==========
    public static final String AUTH_SYSTEM_WORKING = "Authentication system is working!";
    public static final String APP_RUNNING = "Application is running";
    public static final String HEALTH_CHECK_ISSUES = "System health check detected issues";
    
    // ========== ADDITIONAL RESPONSE DESCRIPTIONS ==========
    public static final String AUTH_RESPONSE_DESC = "Authentication response containing JWT token and user information";
    public static final String USER_DTO_DESC = "User data transfer object for API responses";
    public static final String HTTP_STATUS_CODE_DESC = "HTTP status code";
    public static final String RESPONSE_MESSAGE_DESC = "Response message";
    public static final String RESPONSE_DATA_DESC = "Response data";
    public static final String JWT_TOKEN_DESC = "JWT access token for authentication";
    public static final String USER_INFO_DESC = "User information";
    public static final String USER_ID_DESC = "User unique identifier";
    
    // ========== ADDITIONAL EXAMPLES ==========
    public static final String HTTP_STATUS_EXAMPLE = "200";
    public static final String RESPONSE_MESSAGE_EXAMPLE = "Operation completed successfully";
    public static final String JWT_TOKEN_EXAMPLE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
    public static final String USER_ID_EXAMPLE = "1";
    
    // ========== VALIDATION MESSAGES ==========
    // Username validation messages
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String USERNAME_SIZE = "Username must be between 3 and 50 characters";
    public static final String USERNAME_PATTERN = "Username can only contain letters, numbers, and underscores";
    
    // Password validation messages
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_SIZE = "Password must be between 8 and 100 characters";
    public static final String PASSWORD_SIZE_LOGIN = "Password cannot be empty";
    public static final String PASSWORD_PATTERN = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character";
    
    // Full name validation messages
    public static final String FULL_NAME_REQUIRED = "Full name is required";
    public static final String FULL_NAME_SIZE = "Full name must be between 2 and 100 characters";
    public static final String FULL_NAME_PATTERN = "Full name can only contain letters and spaces";
    public static final String FULL_NAME_PATTERN_OPTIONAL = "Full name can only contain letters and spaces";
    
    // Email validation messages
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_VALID = "Email must be a valid email address";
    public static final String EMAIL_SIZE = "Email must not exceed 100 characters";
    
    // Private constructor to prevent instantiation
    private SwaggerDefaultValue() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
