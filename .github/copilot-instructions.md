Developer: # Copilot Instructions for Learn Spring API

## Project Overview

Learn Spring API is a Spring Boot REST API project built for learning purposes, demonstrating comprehensive user management with JWT authentication, MySQL database integration, and modern Spring Boot best practices. The system provides secure user registration, authentication, and CRUD operations with validation and error handling.

## Key Technologies

- **Java**: JDK 21
- **Framework**: Spring Boot 3.5.3 with Spring Web MVC
- **Database**: MySQL with Spring Data JPA/Hibernate
- **Authentication**: JWT tokens with Spring Security 6
- **Security**: BCrypt password encoding, method-level security
- **Documentation**: OpenAPI 3 (Swagger) with SpringDoc
- **Validation**: Jakarta Bean Validation with custom validators
- **Mapping**: MapStruct 1.5.5 for DTO/Entity mapping
- **Testing**: Spring Boot Test with JUnit 5
- **Build Tool**: Maven 3.6+ with annotation processing
- **Logging**: SLF4J with Logback, file rotation

## Architecture Overview

```
src/main/java/com/giatrong/learning/learnspringapi/
├── LearnSpringApiApplication.java    # Main Spring Boot application
├── config/                          # Security & application configuration
│   ├── ApplicationConfig.java       # Authentication beans & password encoder
│   ├── JwtAuthenticationFilter.java # JWT token validation filter
│   ├── SecurityConfig.java          # Spring Security configuration
│   └── OpenAPIConfig.java           # Swagger/OpenAPI documentation setup
├── controller/                      # REST API endpoints
│   ├── UserController.java          # User CRUD operations
│   └── Auth/                        
│       └── AuthController.java      # Authentication endpoints (login/register)
├── service/                         # Business logic layer
│   ├── UserService.java             # User management operations
│   ├── JwtService.java              # JWT token generation & validation
│   └── auth/
│       └── AuthService.java         # Authentication business logic
├── repository/                      # Data access layer
│   ├── UserRepository.java          # User entity repository
│   └── Auth/                        # Authentication-related repositories
├── entity/                          # JPA entities
│   └── User.java                    # User entity with UserDetails implementation
├── dto/                             # Data Transfer Objects
│   ├── request/                     # Request DTOs
│   │   ├── User/                    # User-related requests
│   │   └── Auth/                    # Authentication requests
│   └── response/                    # Response DTOs
│       ├── ApiResponse.java         # Standardized API response wrapper
│       ├── UserDto.java             # User response DTO
│       └── Auth/                    # Authentication response DTOs
├── mapper/                          # MapStruct mapping interfaces
│   └── UserMapper.java              # Entity ↔ DTO mappings
├── exception/                       # Exception handling
│   ├── ApiExceptionHandler.java     # Global exception handler
│   ├── AppException.java            # Custom application exception
│   └── ResourceNotFoundException.java # Resource not found exception
├── enums/                           # Enumerations
│   ├── Role.java                    # User roles (USER, ADMIN)
│   └── ErrorCode.java               # Standardized error codes
├── validation/                      # Custom validation logic
├── util/                            # Utility classes
└── common/                          # Common/shared components
```

## Configuration Files

```
src/main/resources/
├── application.yml                  # Main configuration (port, profiles)
├── application-dev.yml              # Development environment config
├── application-dev-example.yml      # Example dev configuration template
└── banner.txt                      # Custom Spring Boot startup banner
```

## Key Features

- **JWT Authentication**: Stateless authentication with token-based security
- **Role-Based Access Control**: User roles with method-level security annotations
- **Database Integration**: MySQL with JPA/Hibernate, automatic schema updates
- **API Documentation**: Interactive Swagger UI at `/api/v1/docs`
- **Request Validation**: Jakarta Bean Validation with custom error messages
- **Error Handling**: Global exception handling with standardized responses
- **Logging**: Structured logging with file rotation and different log levels
- **Profile Support**: Environment-specific configurations (dev/prod)
- **CORS Support**: Cross-origin resource sharing configuration

## API Endpoints

### Authentication (`/api/v1/auth`)
- `POST /register` - User registration with validation
- `POST /login` - User authentication with JWT token response
- `GET /test` - Public endpoint for system health check

### User Management (`/api/v1/users`)
- `GET /` - Get all users (with role-based access)
- `GET /{id}` - Get user by ID
- `POST /` - Create new user
- `PUT /{id}` - Update user information
- `DELETE /{id}` - Delete user (admin only)

## Security Implementation

- **JWT Filter**: Custom filter for token validation on protected endpoints
- **Password Encoding**: BCrypt for secure password hashing
- **Method Security**: `@PreAuthorize` annotations for role-based access
- **Stateless Sessions**: No server-side session storage
- **Authentication Provider**: Custom DAO authentication with UserDetailsService

## Development Guidelines

- **DTOs**: Use separate DTOs for requests/responses, never expose entities directly
- **Validation**: Apply `@Valid` on request bodies and method parameters
- **Logging**: Use SLF4J with structured logging patterns
- **Error Handling**: Throw specific exceptions, handle globally in `ApiExceptionHandler`
- **Testing**: Write unit tests for services and integration tests for controllers
- **Documentation**: Use OpenAPI annotations (`@Operation`, `@Schema`) for API docs
- **Security**: Always validate user permissions before data access operations

## Database Schema

The application uses MySQL with automatic schema generation through Hibernate DDL. Key entities include:
- **Users**: Core user information with roles and authentication details
- Future entities can be added following the same pattern

## Build & Deployment

- **Maven**: Use `mvn spring-boot:run` for development
- **Profiles**: Activate `dev` profile for development, `prod` for production
- **Logging**: Application logs are written to `logs/application.log` with rotation
- **Port**: Default development port is 8080, configurable per environment

## Response Format Standards

All API responses should follow a consistent format using the `ApiResponse<T>` wrapper:

```java
// Success Response
ApiResponse<UserDto> response = ApiResponse.success(
    userData, 
    "Operation successful", 
    HttpStatus.OK.value()
);

// Error Response
ApiResponse<String> errorResponse = ApiResponse.error(
    "Error message", 
    HttpStatus.BAD_REQUEST.value(),
    ErrorCode.VALIDATION_ERROR
);
```

**Standard Response Structure:**
- `data`: The actual response payload (generic type T)
- `message`: Human-readable message describing the operation result
- `status`: HTTP status code
- `timestamp`: ISO 8601 formatted timestamp
- `success`: Boolean indicating operation success/failure
- `errorCode`: Standardized error code (for error responses)

## Error Handling Guidelines

### Global Exception Handling
- Use `@ControllerAdvice` in `ApiExceptionHandler` for centralized error handling
- Map specific exceptions to appropriate HTTP status codes using `ErrorCode` enum
- Always return standardized `ApiResponse<String>` for errors
- Include meaningful error messages and standardized error codes

### Error Code Categories
The `ErrorCode` enum organizes errors into logical categories:

- **System Errors**: 
  - `UNCATEGORIZED_EXCEPTION`: `HttpStatus.INTERNAL_SERVER_ERROR` (500)
  - `INTERNAL_SERVER_ERROR`: `HttpStatus.INTERNAL_SERVER_ERROR` (500)
  - `SERVICE_UNAVAILABLE`: `HttpStatus.SERVICE_UNAVAILABLE` (503)
  - `DATABASE_ERROR`: `HttpStatus.INTERNAL_SERVER_ERROR` (500)

- **Validation Errors**: 
  - `VALIDATION_FAILED`: `HttpStatus.BAD_REQUEST` (400)
  - `INVALID_INPUT`: `HttpStatus.BAD_REQUEST` (400)
  - `MISSING_REQUIRED_FIELD`: `HttpStatus.BAD_REQUEST` (400)
  - `INVALID_FORMAT`: `HttpStatus.BAD_REQUEST` (400)
  - `INVALID_JSON`: `HttpStatus.BAD_REQUEST` (400)

- **Authentication Errors**: 
  - `UNAUTHENTICATED`: `HttpStatus.UNAUTHORIZED` (401)
  - `INVALID_CREDENTIALS`: `HttpStatus.UNAUTHORIZED` (401)
  - `TOKEN_EXPIRED`: `HttpStatus.UNAUTHORIZED` (401)
  - `INVALID_TOKEN`: `HttpStatus.UNAUTHORIZED` (401)
  - `TOKEN_MISSING`: `HttpStatus.UNAUTHORIZED` (401)

- **Authorization Errors**: 
  - `ACCESS_DENIED`: `HttpStatus.FORBIDDEN` (403)
  - `INSUFFICIENT_PRIVILEGES`: `HttpStatus.FORBIDDEN` (403)
  - `RESOURCE_FORBIDDEN`: `HttpStatus.FORBIDDEN` (403)

- **User Errors**: 
  - `USER_NOT_FOUND`: `HttpStatus.NOT_FOUND` (404)
  - `USER_ALREADY_EXISTS`: `HttpStatus.CONFLICT` (409)
  - `USERNAME_ALREADY_TAKEN`: `HttpStatus.CONFLICT` (409)
  - `EMAIL_ALREADY_TAKEN`: `HttpStatus.CONFLICT` (409)
  - `USER_DISABLED`: `HttpStatus.FORBIDDEN` (403)
  - `USER_LOCKED`: `HttpStatus.FORBIDDEN` (403)

- **Validation Specific**: 
  - `USERNAME_INVALID`: `HttpStatus.BAD_REQUEST` (400)
  - `PASSWORD_INVALID`: `HttpStatus.BAD_REQUEST` (400)
  - `EMAIL_INVALID`: `HttpStatus.BAD_REQUEST` (400)
  - `PHONE_INVALID`: `HttpStatus.BAD_REQUEST` (400)
  - `DOB_INVALID`: `HttpStatus.BAD_REQUEST` (400)
  - `PASSWORD_TOO_WEAK`: `HttpStatus.BAD_REQUEST` (400)

- **Resource Errors**: 
  - `RESOURCE_NOT_FOUND`: `HttpStatus.NOT_FOUND` (404)
  - `RESOURCE_ALREADY_EXISTS`: `HttpStatus.CONFLICT` (409)
  - `RESOURCE_CONFLICT`: `HttpStatus.CONFLICT` (409)

- **Request Errors**: 
  - `BAD_REQUEST`: `HttpStatus.BAD_REQUEST` (400)
  - `INVALID_PARAMETER`: `HttpStatus.BAD_REQUEST` (400)
  - `MISSING_PARAMETER`: `HttpStatus.BAD_REQUEST` (400)
  - `METHOD_NOT_ALLOWED`: `HttpStatus.METHOD_NOT_ALLOWED` (405)
  - `UNSUPPORTED_MEDIA_TYPE`: `HttpStatus.UNSUPPORTED_MEDIA_TYPE` (415)

- **Rate Limiting & Quota**: 
  - `RATE_LIMIT_EXCEEDED`: `HttpStatus.TOO_MANY_REQUESTS` (429)
  - `QUOTA_EXCEEDED`: `HttpStatus.TOO_MANY_REQUESTS` (429)

### Custom Exceptions
- `AppException`: Generic application exception with `ErrorCode` enum support
- `ResourceNotFoundException`: For entity not found scenarios (maps to `USER_NOT_FOUND`, `RESOURCE_NOT_FOUND`)
- Throw specific exceptions in service layer, handle globally in `ApiExceptionHandler`

### ErrorCode Usage Pattern
```java
// Throw exception with specific error code
if (!userRepository.existsById(id)) {
    throw new AppException(ErrorCode.USER_NOT_FOUND);
}

// For validation errors
if (userRepository.existsByUsername(username)) {
    throw new AppException(ErrorCode.USERNAME_ALREADY_TAKEN);
}

// For authentication errors
if (!jwtService.isTokenValid(token)) {
    throw new AppException(ErrorCode.TOKEN_EXPIRED);
}
```

### Validation Errors
- Use Jakarta Bean Validation annotations (`@Valid`, `@NotBlank`, `@Email`, etc.)
- Map validation failures to appropriate `ErrorCode` (e.g., `EMAIL_INVALID`, `PASSWORD_INVALID`)
- Custom validation messages support parameterized templates (e.g., `"must be at least {min} characters"`)
- Validation errors automatically handled by global exception handler

### Error Response Structure
```java
// Standard error response using ErrorCode
ApiResponse<String> errorResponse = ApiResponse.error(
    errorCode.getMessage(),
    errorCode.getStatusCode().value(),
    errorCode
);
```

### Legacy Compatibility
Maintain backward compatibility with legacy error codes:
- `USER_EXISTED` → `USER_ALREADY_EXISTS`
- `USER_NOT_EXISTED` → `USER_NOT_FOUND`
- `UNAUTHORIZED` → `ACCESS_DENIED`

## Authentication Middleware

### JWT Authentication Filter
The `JwtAuthenticationFilter` extends `OncePerRequestFilter` and handles:

1. **Token Extraction**: Extract JWT from Authorization header (`Bearer <token>`)
2. **Token Validation**: Validate token signature and expiration
3. **User Loading**: Load user details from database using username from token
4. **Security Context**: Set authentication in `SecurityContextHolder`

### Filter Chain Order
```java
.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
```

### Public Endpoints
Configure public endpoints in `SecurityConfig`:
- `/api/v1/auth/**` - Authentication endpoints
- `/swagger-ui/**` - API documentation
- `/v3/api-docs/**` - OpenAPI specs

## Database Models & Relationships

### Entity Design Patterns
- Use JPA annotations (`@Entity`, `@Table`, `@Column`)
- Implement `UserDetails` for authentication entities
- Use Lombok for reducing boilerplate (`@Data`, `@Builder`, `@Entity`)
- Follow naming conventions: entity classes in `entity/` package

### User Entity Structure
```java
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    // UserDetails implementation methods
}
```

### Repository Pattern
- Extend `JpaRepository<Entity, ID>`
- Use method naming conventions for query derivation
- Custom queries with `@Query` annotation when needed

## Authentication System

### JWT Token Management
- **Generation**: Create tokens with user claims and expiration
- **Validation**: Verify signature, expiration, and user existence
- **Secret Key**: Use secure, environment-specific JWT secret
- **Expiration**: Configure appropriate token lifetime

### Password Security
- Use `BCryptPasswordEncoder` for password hashing
- Never store plain text passwords
- Hash passwords before saving to database

### Role-Based Access Control
```java
// Method-level security
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }

// Controller-level security
@SecurityRequirement(name = "bearerAuth")
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) { ... }
```

### Authentication Flow
1. **Registration**: Hash password → Save user → Generate JWT
2. **Login**: Authenticate credentials → Load user → Generate JWT
3. **Request**: Extract token → Validate → Load user → Set security context
4. **Authorization**: Check roles/permissions for endpoint access
