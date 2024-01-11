package com.setokk.bookpublisher.config;

import com.setokk.bookpublisher.service.BookService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic booksPublishedTopic() {
        return TopicBuilder.name(BookService.BOOK_PUBLISHED_TOPIC)
                .partitions(10)
                .replicas(1)
                .build();
    }
}
