package com.giatrong.learning.learnspringapi.service;

import com.giatrong.learning.learnspringapi.dto.request.User.UserCreateRequest;
import com.giatrong.learning.learnspringapi.dto.request.User.UserListRequest;
import com.giatrong.learning.learnspringapi.dto.request.User.UserUpdateRequest;
import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import com.giatrong.learning.learnspringapi.entity.User;
import com.giatrong.learning.learnspringapi.enums.Role;
import com.giatrong.learning.learnspringapi.exception.AppException;
import com.giatrong.learning.learnspringapi.exception.ResourceNotFoundException;
import com.giatrong.learning.learnspringapi.mapper.UserMapper;
import com.giatrong.learning.learnspringapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder; // Giả sử bạn có tiêm PasswordEncoder
import org.springframework.stereotype.Service;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Counter userCreationCounter;
    private final Timer userFetchTimer;

    public Page<UserDto> getAllUsers(UserListRequest userListRequest, Pageable pageable) {
        log.info("Getting all users and filtering with request: {}", userListRequest);
        // allOf() để khởi tạo Specification rỗng -> AND với các điều kiện khác
        Specification<User> spec = Specification.allOf();

        if(userListRequest.getId() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("id"), userListRequest.getId()));
        }

        if (userListRequest.getFullName() != null && !userListRequest.getFullName().isBlank()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + userListRequest.getFullName().toLowerCase() + "%"));
        }

        if (userListRequest.getUsername() != null && !userListRequest.getUsername().isBlank()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + userListRequest.getUsername().toLowerCase() + "%"));
        }

        if (userListRequest.getEmail() != null && !userListRequest.getEmail().isBlank()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + userListRequest.getEmail().toLowerCase() + "%"));
        }

        if (userListRequest.getRole() != null && !userListRequest.getRole().isBlank()) {
            try {
                // Chuyển đổi từ String trong DTO sang Enum để truy vấn
                Role roleEnum = Role.valueOf(userListRequest.getRole().toUpperCase());
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("role"), roleEnum));
            } catch (IllegalArgumentException e) {
                log.warn("Invalid role value provided for filtering: {}", userListRequest.getRole());
                // Có thể bỏ qua hoặc ném ra một lỗi tùy chỉnh
            }
        }

        Page<User> userPage = userRepository.findAll(spec, pageable);
        log.info("Found {} users", userPage.getTotalElements());
        return userPage.map(userMapper::toDto);
    }

    @Timed(value= "user.fetch.time", description = "Time spent fetching user by ID")
    // use @Timed annotation to track performance
    // This will automatically create a timer metric for this method
    // and record the time taken to execute it
    @Cacheable(value = "users", key = "#id")
    public UserDto getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        log.info("Đang gọi xuống Database để lấy user với id: {}", id);
        try {
            Thread.sleep(2000); // Giả lập độ trễ 2 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserDto user = userRepository.findById(id)
                // Dùng instance mapper đã được tiêm vào
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    throw new ResourceNotFoundException("User not found with id: " + id);
                });
        log.info("Successfully retrieved user with id: {}", id);
        return user;
    }

    @Timed(value= "user.creation.time", description = "Time spent creating a user")
    public UserDto createUser(UserCreateRequest request) {
        log.info("Creating user {}", request);
        // Kiểm tra xem username đã tồn tại chưa (ví dụ)
        if (userRepository.existsUsersByFullName(request.getFullName())) {
            log.warn("User with name {} already exists", request.getFullName());
            throw new IllegalArgumentException("Username already exists");
        }

        // Dùng mapper để chuyển đổi an toàn từ Request DTO sang Entity
        User user = userMapper.toEntity(request);

        // Xử lý logic nghiệp vụ không thuộc về mapper (như mã hóa mật khẩu)
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        log.info("Created user {}", savedUser);
        UserDto result = userMapper.toDto(savedUser);
        
        // Increment counter
        userCreationCounter.increment();
        log.info("User created successfully with metrics tracked");
        
        return result;
    }

    @CachePut(value = "users", key = "#userDto.id") // Luôn cập nhật cache
    public UserDto updateUser(Long id, UserUpdateRequest request) {
        log.info("Updating user with id: {} with request: {}", id, request);
        
        // 1. Tìm user hiện tại trong DB
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {} for update", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });

        // 2. Dùng mapper để cập nhật các trường từ DTO vào entity đã có
        // Mapper sẽ tự động bỏ qua các trường null và các trường được đánh dấu @Mapping(ignore=true)
        userMapper.updateEntityFromDto(request, existingUser);

        User updatedUser = userRepository.save(existingUser);
        log.info("Successfully updated user with id: {}", id);

        return userMapper.toDto(updatedUser);
    }

    @CacheEvict(value = "users", key = "#id") // Xóa khỏi cache
    public void deleteUser(Long id) {
        log.info("Attempting to delete user with id: {}", id);
        
        // Kiểm tra xem user có tồn tại không trước khi xóa
        if (!userRepository.existsById(id)) {
            log.error("Cannot delete user - user not found with id: {}", id);
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        
        userRepository.deleteById(id);
        log.info("Successfully deleted user with id: {}", id);
    }
}