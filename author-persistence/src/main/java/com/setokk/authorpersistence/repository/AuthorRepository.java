package com.setokk.authorpersistence.repository;

import com.setokk.authorpersistence.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
