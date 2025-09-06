package com.giatrong.learning.learnspringapi.controller.Kafka;

import com.giatrong.learning.learnspringapi.producer.MultiPartitionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/partition")
public class PartitionController {
    private final MultiPartitionProducer producer;

    @PostMapping
    public String send(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Sent: " + msg;
    }
}