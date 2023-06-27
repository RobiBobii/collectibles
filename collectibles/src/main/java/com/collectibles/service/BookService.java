package com.collectibles.service;

import com.collectibles.domain.Book;
import com.collectibles.exceptions.BookNotFoundException;
import com.collectibles.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(final Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long id) throws BookNotFoundException {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookNotFoundException();
        }
    }
}