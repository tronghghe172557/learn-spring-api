package com.giatrong.learning.learnspringapi.controller.Kafka;

import com.giatrong.learning.learnspringapi.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;

    @PostMapping("/send")
    public String send(@RequestParam String message) {
        producerService.sendMessage("test-topic", message);
        return "Message sent: " + message;
    }
}