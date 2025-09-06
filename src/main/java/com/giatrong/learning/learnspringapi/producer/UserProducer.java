package com.giatrong.learning.learnspringapi.producer;

import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendUserEvent(UserDto event) {
        log.info("Sending UserEvent: {}", event);
        kafkaTemplate.send("user-topic", event);
    }
}