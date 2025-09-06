package com.giatrong.learning.learnspringapi.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultiPartitionProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("multi-partition-topic", msg);
        System.out.println("➡️ Sent: " + msg);
    }
}