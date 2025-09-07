package com.giatrong.learning.learnspringapi.controller.Kafka;

import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import com.giatrong.learning.learnspringapi.entity.Role;
import com.giatrong.learning.learnspringapi.mapper.UserMapper;
import com.giatrong.learning.learnspringapi.producer.UserProducer;
import com.giatrong.learning.learnspringapi.entity.User;
import com.giatrong.learning.learnspringapi.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class KafkaUserController {

    private static final Logger log = LoggerFactory.getLogger(KafkaUserController.class);
    private final UserProducer userProducer;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name) {
        return "User registered: ";
    }
}