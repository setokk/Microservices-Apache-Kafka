package com.setokk.authorpersistence.listener;

import com.setokk.authorpersistence.model.Author;
import com.setokk.authorpersistence.service.AuthorService;

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

    private final AuthorService authorService;

    @Autowired
    public BookPublishedListener(AuthorService authorService) {
        this.authorService = authorService;
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

        log.info(message);
    }

    private Author getAuthorFromJSON(String s) {
        JSONObject json = new JSONObject(s);
        JSONObject author = json.getJSONObject("author");

        return Author.builder()
                .name(author.getString("name"))
                .build();
    }
}
