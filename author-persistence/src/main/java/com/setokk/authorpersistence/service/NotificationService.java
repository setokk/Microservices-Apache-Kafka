package com.setokk.authorpersistence.service;

import com.setokk.authorpersistence.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final String NOTIFICATION_PUBLISHED_TOPIC = "notification.published";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Notification notification) {
        String payload = "[" + notification.timestamp() + "]: " + notification.message();
        kafkaTemplate.send(NOTIFICATION_PUBLISHED_TOPIC, payload);
    }
}
