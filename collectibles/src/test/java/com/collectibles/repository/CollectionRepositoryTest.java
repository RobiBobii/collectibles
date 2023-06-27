package com.collectibles.repository;

import com.collectibles.domain.Book;
import com.collectibles.domain.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBooksCollectionRepositorySave() {
        //Given
        Collection collection = new Collection();

        //When
        collectionRepository.save(collection);

        //Then
        Long id = collection.getId();
        Optional<Collection> testBooksCollection = collectionRepository.findById(id);
        assertTrue(testBooksCollection.isPresent());

        //Cleanup
        collectionRepository.deleteById(id);
    }

    @Test
    void testBooksCollectionRepositoryFindAll() {
        //Given
        Collection collection = new Collection();

        //When
        collectionRepository.save(collection);
        Long id = collection.getId();
        List<Collection> testCollections = collectionRepository.findAll();

        //Then
        assertNotNull(testCollections);
        assertNotEquals(0, testCollections.size());

        //Cleanup
        collectionRepository.deleteById(id);
    }

    @Test
    void testBooksCollectionRepositorySave_shouldSaveBooks() {
        //Given
        Book book = new Book();
        Collection collection = new Collection();
        collection.getBooks().add(book);

        //When
        collectionRepository.save(collection);
        Long collectionId = collection.getId();
        Long bookId = book.getId();
        Optional<Book> testBook = bookRepository.findById(bookId);

        //Then
        assertTrue(testBook.isPresent());

        //Cleanup
        collectionRepository.deleteById(collectionId);
        bookRepository.deleteById(bookId);
    }

    @Test
    void testBooksCollectionRepositoryDelete_shouldNotDeleteBooks() {
        //Given
        Book book = new Book();
        Collection collection = new Collection();
        collection.getBooks().add(book);

        //When
        collectionRepository.save(collection);
        Long collectionId = collection.getId();
        Long bookId = book.getId();
        collectionRepository.deleteById(collectionId);
        Optional<Book> testBook = bookRepository.findById(bookId);

        //Then
        assertTrue(testBook.isPresent());

        //Cleanup
        bookRepository.deleteById(bookId);
    }
}