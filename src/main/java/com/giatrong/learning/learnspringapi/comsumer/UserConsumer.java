package com.giatrong.learning.learnspringapi.comsumer;

import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumer {

//    @KafkaListener(topics = "user-topic-3", groupId = "user-group-2")
    public void listen(UserDto event) {
        try {
            log.info("📩 Received UserEvent: {}", event);

        } catch (Exception e) {
            System.err.println("!!!!!!!!!!!!!! LỖI NGHIÊM TRỌNG TRONG KAFKA LISTENER !!!!!!!!!!!!!!");
            e.printStackTrace();
        }
    }
}
