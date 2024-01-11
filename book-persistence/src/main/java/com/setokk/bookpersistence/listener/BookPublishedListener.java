package com.setokk.bookpersistence.listener;

import com.setokk.bookpersistence.model.Book;
import com.setokk.bookpersistence.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BookPublishedListener {
    public static final String BOOK_PUBLISHED_TOPIC = "book.published";

    private final BookService bookService;

    @Autowired
    public BookPublishedListener(BookService bookService) {
        this.bookService = bookService;
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

        log.info(message);
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
