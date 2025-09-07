package com.giatrong.learning.learnspringapi.repository;

import com.giatrong.learning.learnspringapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Đánh dấu đây là một Bean thuộc tầng Repository để Spring quản lý
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findUserByFullName(String fullName);

    User findUserByUsername(String username);

    User findUserById(Long id);

    boolean existsUsersByFullName(String fullName);

    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}