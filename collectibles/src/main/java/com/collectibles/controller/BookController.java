package com.collectibles.controller;

import com.collectibles.domain.Book;
import com.collectibles.domain.dto.BookDto;
import com.collectibles.exceptions.BookNotFoundException;
import com.collectibles.mapper.BookMapper;
import com.collectibles.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) throws BookNotFoundException {
        return new ResponseEntity<>(bookMapper.mapToBookDto(bookService.getBook(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(bookMapper.mapToBookDto(savedBook));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
        return ResponseEntity.ok().build();
    }
}