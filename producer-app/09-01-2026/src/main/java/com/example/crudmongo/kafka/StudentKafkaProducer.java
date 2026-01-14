package com.example.crudmongo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "student-topic";

    public StudentKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStudentEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("📤 Sent to Kafka: " + message);
    }
}