package com.setokk.bookpublisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final static String BOOK_PUBLISHED_TOPIC = "book.published";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public BookService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String bookJSON) {
        kafkaTemplate.send(BOOK_PUBLISHED_TOPIC, bookJSON);
    }
}
