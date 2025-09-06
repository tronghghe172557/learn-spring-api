package com.giatrong.learning.learnspringapi.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerOne {
//    @KafkaListener(topics = "multi-partition-topic", groupId = "group-A")
    public void listen(String msg) {
        log.info("👤 ConsumerOne received: {}", msg);
    }
}

