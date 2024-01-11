package com.setokk.bookpublisher.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookRequest {
    private String title;
    private String isbn;
    private AuthorJSON author;
}
