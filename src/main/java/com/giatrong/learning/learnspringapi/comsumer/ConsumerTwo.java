package com.giatrong.learning.learnspringapi.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerTwo {
//    @KafkaListener(topics = "multi-partition-topic", groupId = "group-A")
    public void listen(String msg) {
        log.info("👤 ConsumerTwo received: {}", msg);
    }
}