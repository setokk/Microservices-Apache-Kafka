package com.setokk.bookpublisher.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthorJSON {
    private Long id;
    private String name;
}
