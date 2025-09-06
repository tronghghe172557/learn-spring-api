package com.giatrong.learning.learnspringapi.controller.Kafka;

import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import com.giatrong.learning.learnspringapi.mapper.UserMapper;
import com.giatrong.learning.learnspringapi.producer.UserProducer;
import com.giatrong.learning.learnspringapi.entity.User;
import com.giatrong.learning.learnspringapi.enums.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class KafkaUserController {

    private static final Logger log = LoggerFactory.getLogger(KafkaUserController.class);
    private final UserProducer userProducer;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name) {
        User event = new User(name, "Khongcopass@2003", "KafkaUser hehe", "hoanggiatrang01@gmail.com", Role.USER);
        UserDto userDto = userMapper.toDto(event);
        log.info("Registering user {}", userDto);
        userProducer.sendUserEvent(userDto);
        return "User registered: " + userDto;
    }
}