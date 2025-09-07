package com.giatrong.learning.learnspringapi.service.auth;

import com.giatrong.learning.learnspringapi.dto.request.Auth.LoginRequest;
import com.giatrong.learning.learnspringapi.dto.request.Auth.RegisterRequest;
import com.giatrong.learning.learnspringapi.dto.response.Auth.AuthResponse;
import com.giatrong.learning.learnspringapi.entity.Role;
import com.giatrong.learning.learnspringapi.entity.User;
import com.giatrong.learning.learnspringapi.enums.ErrorCode;
import com.giatrong.learning.learnspringapi.exception.AppException;
import com.giatrong.learning.learnspringapi.repository.RoleRepository;
import com.giatrong.learning.learnspringapi.repository.UserRepository;
import com.giatrong.learning.learnspringapi.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // hash passwords
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackOn =  Exception.class)
    public AuthResponse register(RegisterRequest request) {
        log.info("Registering user -> start: {}", request);
        
        // 1. Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_TAKEN);
        }
        
        // 2. Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_TAKEN);
        }
        
        // 3. Create a new User object from the RegisterRequest
        var user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // 4. Set default USER role
        Role defaultRole = roleRepository.findByCode("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.getRoles().add(defaultRole);

        // 5. Save the User to the database
        var savedUser = userRepository.save(user);

        // 6. Generate a JWT token for the saved user
        var jwtToken = jwtService.generateToken(savedUser);

        log.info("Registering user -> end: {}", savedUser);
        return AuthResponse.builder().token(jwtToken).user(savedUser).build();
    }

    @Transactional(rollbackOn =  Exception.class)
    public AuthResponse login(LoginRequest request) {
        // 1. use Spring Security to authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. if authentication is successful, find the user by username
        var user = userRepository.findUserByUsername(request.getUsername());

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwtToken).user(user).build();
    }
}