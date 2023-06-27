package com.collectibles.mapper;

import com.collectibles.domain.Book;
import com.collectibles.domain.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .year(bookDto.getYear())
                .note(bookDto.getNote())
                .build();
    }

    public BookDto mapToBookDto(final Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .year(book.getYear())
                .note(book.getNote())
                .build();
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}