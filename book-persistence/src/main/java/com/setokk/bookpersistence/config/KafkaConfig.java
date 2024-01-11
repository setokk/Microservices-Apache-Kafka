package com.setokk.bookpersistence.config;

import com.setokk.bookpersistence.listener.BookPublishedListener;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic booksPublishedTopic() {
        return TopicBuilder.name(BookPublishedListener.BOOK_PUBLISHED_TOPIC)
                .partitions(10)
                .replicas(1)
                .build();
    }
}
