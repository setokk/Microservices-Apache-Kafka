package com.setokk.authorpersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_id_seq_gen"
    )
    @SequenceGenerator(
            name = "author_id_seq_gen",
            sequenceName = "author_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String name;
}
