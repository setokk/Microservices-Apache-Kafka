package com.setokk.authorpersistence.listener;

import com.setokk.authorpersistence.model.Author;
import com.setokk.authorpersistence.model.Notification;
import com.setokk.authorpersistence.service.AuthorService;
import com.setokk.authorpersistence.service.NotificationService;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BookPublishedListener {
    private static final String BOOK_PUBLISHED_TOPIC = "book.published";

    private final AuthorService authorService;
    private final NotificationService notificationService;

    @Autowired
    public BookPublishedListener(AuthorService authorService,
                                 NotificationService notificationService) {
        this.authorService = authorService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = BOOK_PUBLISHED_TOPIC)
    public void listens(String payload) {
        log.info("Received book {}", payload);

        Author author = getAuthorFromJSON(payload);
        Author savedAuthor = authorService.save(author);
        String message = String.format(
                "Author '%s' [%s] saved!",
                savedAuthor.getName(),
                savedAuthor.getId()
        );

        notificationService.publish(new Notification(message, LocalDateTime.now()));
    }

    private Author getAuthorFromJSON(String s) {
        JSONObject json = new JSONObject(s);
        JSONObject author = json.getJSONObject("author");

        return Author.builder()
                .name(json.getString("name"))
                .build();
    }
}
