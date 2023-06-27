package com.collectibles.mapper;

import com.collectibles.domain.Book;
import com.collectibles.domain.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void shouldMapToBook() {
        //Given
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getNote(), book.getNote());
    }

    @Test
    void shouldMapToBookDto() {
        //Given
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();

        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);

        //Then
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
        assertEquals(book.getYear(), bookDto.getYear());
        assertEquals(book.getNote(), bookDto.getNote());
    }

    @Test
    void shouldMapToBookDtoList() {
        //Given
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
        List<Book> books = List.of(book);

        //When
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(books);

        //Then
        assertEquals(books.size(), bookDtos.size());
        assertEquals(books.get(0).getId(), bookDtos.get(0).getId());
        assertEquals(books.get(0).getTitle(), bookDtos.get(0).getTitle());
        assertEquals(books.get(0).getAuthor(), bookDtos.get(0).getAuthor());
        assertEquals(books.get(0).getYear(), bookDtos.get(0).getYear());
        assertEquals(books.get(0).getNote(), bookDtos.get(0).getNote());
    }
}