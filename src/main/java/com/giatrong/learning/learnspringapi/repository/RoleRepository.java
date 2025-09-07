package com.giatrong.learning.learnspringapi.repository;

import com.giatrong.learning.learnspringapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByCode(String code);

    Set<Role> findByCodeIn(Set<String> roleCodes);
    
    boolean existsByCode(String code);
}
