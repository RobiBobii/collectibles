package com.collectibles.domain.dto;

import lombok.Builder;

@Builder
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String year;
    private String note;

    public BookDto(Long id, String title, String author, String year, String note) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.note = note;
    }

    public BookDto() {}

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
}