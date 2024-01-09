package com.setokk.bookpersistence.listener;

import com.setokk.bookpersistence.model.Book;
import com.setokk.bookpersistence.model.Notification;
import com.setokk.bookpersistence.service.BookService;
import com.setokk.bookpersistence.service.NotificationService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookPublishedListener {
    private static Logger log = LoggerFactory.getLogger(BookPublishedListener.class);
    private static final String BOOK_PUBLISHED_TOPIC = "book.published";

    private final BookService bookService;
    private final NotificationService notificationService;

    @Autowired
    public BookPublishedListener(BookService bookService,
                                 NotificationService notificationService) {
        this.bookService = bookService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = BOOK_PUBLISHED_TOPIC)
    public void listens(String payload) {
        log.info("Received book {}", payload);

        Book book = getBookFromJSON(payload);
        Book savedBook = bookService.save(book);
        String message = String.format(
                "Book '%s' [%s] saved!",
                savedBook.getTitle(),
                savedBook.getId()
        );

        notificationService.publish(new Notification(message, LocalDateTime.now()));
    }

    private Book getBookFromJSON(String s) {
        JSONObject json = new JSONObject(s);
        JSONObject author = json.getJSONObject("author");

        return Book.builder()
                .title(json.getString("title"))
                .isbn(json.getString("isbn"))
                .authorId(author.getLong("id"))
                .build();
    }
}
