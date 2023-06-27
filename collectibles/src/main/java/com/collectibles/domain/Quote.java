package com.collectibles.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="quotes")
public class Quote {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id", unique=true)
    private Long id;

    @Column(name="author")
    private String author;

    @Column(name="content")
    private String content;

    public Quote(String author, String content) {
        this.author = author;
        this.content = content;
    }
}