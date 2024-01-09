package com.setokk.bookpersistence.repository;

import com.setokk.bookpersistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
