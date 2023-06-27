package com.collectibles.domain;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id", unique=true)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="year")
    private String year;

    @Column(name= "note")
    private String note;

    @ManyToOne
    @JoinColumn(name= "collection_id")
    private Collection collection;

    public Book(Long id, String title, String author, String year, String note, Collection collection) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.note = note;
        this.collection = collection;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getNote() {
        return note;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}