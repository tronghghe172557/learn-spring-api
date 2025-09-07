package com.giatrong.learning.learnspringapi.config;

import com.giatrong.learning.learnspringapi.entity.Role;
import com.giatrong.learning.learnspringapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles() {
        // Create USER role
        if (!roleRepository.existsByCode("USER")) {
            Role userRole = Role.builder()
                .code("USER")
                .name("User")
                .description("Basic user role")
                .build();
            roleRepository.save(userRole);
            log.info("Created role: USER");
        }

        // Create ADMIN role
        if (!roleRepository.existsByCode("ADMIN")) {
            Role adminRole = Role.builder()
                .code("ADMIN")
                .name("Administrator")
                .description("Administrator role with full permissions")
                .build();
            roleRepository.save(adminRole);
            log.info("Created role: ADMIN");
        }
    }
}
