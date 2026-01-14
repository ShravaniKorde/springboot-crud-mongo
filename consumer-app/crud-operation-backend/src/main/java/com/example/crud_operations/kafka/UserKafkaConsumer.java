package com.example.crud_operations.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaConsumer {

    @KafkaListener(
        topics = "student-topic",
        groupId = "user-consumer-group"
    )
    public void listen(String message) {
        if (message != null && !message.trim().isEmpty()) {
            System.out.println("📥 Received from Kafka: " + message);
        }
    }
}
