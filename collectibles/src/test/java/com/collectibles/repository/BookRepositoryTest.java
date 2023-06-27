package com.collectibles.repository;

import com.collectibles.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book createTestBook() {
        return Book.builder()
                .title("title")
                .author("author")
                .year("year")
                .note("note")
                .build();
    }

    @Test
    void testBookRepositoryFindAll() {
        //Given
        Book book = createTestBook();

        //When
        bookRepository.save(book);
        Long id = book.getId();
        List<Book> testBooks = bookRepository.findAll();

        //Then
        assertNotNull(testBooks);
        assertNotEquals(0, testBooks.size());

        //Cleanup
        bookRepository.deleteById(id);
    }

    @Test
    void testBookRepositorySave() {
        //Given
        Book book = createTestBook();

        //When
        bookRepository.save(book);

        //Then
        Long id = book.getId();
        Optional<Book> testBook = bookRepository.findById(id);
        assertTrue(testBook.isPresent());

        //Cleanup
        bookRepository.deleteById(id);
    }

    @Test
    void testBookRepositoryFindById() {
        //Given
        Book book = createTestBook();
        //When
        bookRepository.save(book);
        Long id = book.getId();
        Optional<Book> testBook = bookRepository.findById(id);

        //Then
        assertNotNull(testBook);

        //Cleanup
        bookRepository.deleteById(id);
    }
}